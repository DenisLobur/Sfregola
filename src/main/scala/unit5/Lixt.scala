package unit5

import scala.annotation.targetName

enum Lixt[+A]:
  case Nil
  @targetName("cons") case ::(hd: A, tl: Lixt[A])

  def head: A =
    this match
      case Nil => throw new NoSuchElementException
      case h :: _ => h

  def tail: Lixt[A] =
    this match
      case Nil => throw new NoSuchElementException
      case _ :: tl => tl

  @targetName("cons")
  def ::[B >: A](h: B): Lixt[B] =
    Lixt.::(h, this)

  @targetName("prepend")
  def :::[B >: A](prefix: Lixt[B]): Lixt[B] =
    prefix match
      case Nil => this
      case head :: tail => head :: tail ::: this

  def reverse: Lixt[A] =
    foldLeft(empty)((acc, x) => x :: acc)

  def foldLeft[B](z: B)(f: (B, A) => B): B =
    this match
      case Nil => z
      case h :: tl => tl.foldLeft(f(z, h))(f)

  def foldRight[B](z: B)(f: (A, B) => B): B =
    reverse.foldLeft(z)((b, a) => f(a, b))

  def mkStr(start: String, sep: String, end: String): String =
    this match
      case Nil => start + end
      case head :: Nil => start + head + end
      case head :: tail => tail.mkStr(start + head + sep, sep, end)

  override def toString: String =
    this match
      case Nil => mkStr(s"Lixt[Nothing](", ", ", ")")
      case head :: _ => mkStr(s"Lixt[${head.getClass.getSimpleName}](", ", ", ")")


  def map[B](f: A => B): Lixt[B] =
    this match
      case Nil => Nil
      case head :: tail => f(head) :: tail.map(f)

  def flatten: Lixt[A] =
    this match
      case Nil => Nil
      case head :: tail => head :: tail.flatten

  def flatMap[B](f: A => Lixt[B]): Lixt[B] =
    this match
      case Nil => Nil
      case head :: tail => f(head) ::: tail.flatMap(f)

  def headOption: Option[A] =
    this match
      case Nil => None
      case head :: _ => Some(head)

  def length: Int =
    this match
      case Nil => 0
      case _ :: tail => 1 + tail.length

  def apply(n: Int): A =
    if (n < 0 || n >= this.length) throw IndexOutOfBoundsException(s"No element at index $n")
    val dropped = this.drop(n)
    dropped.head


  def isEmpty: Boolean =
    this == Lixt.Nil

  def drop(n: Int): Lixt[A] =
    if n <= 0 || isEmpty
    then this
    else tail.drop(n - 1)

  def take(n: Int): Lixt[A] =
    if (n <= 0 || isEmpty) then Nil
    else this match
      case Nil => Nil
      case _ :: Nil => this
      case head :: tail => head :: tail.take(n - 1)

  def dropWhile(p: A => Boolean): Lixt[A] =
    this match
      case Nil => Nil
      case head :: tail => if (p(head)) tail.dropWhile(p) else head :: tail

  def takeWhile(p: A => Boolean): Lixt[A] =
    this match
      case Nil => Nil
      case head :: tail => if (p(head)) head :: tail.takeWhile(p) else Nil

  def find(p: A => Boolean): Option[A] =
    this match
      case Nil => None
      case head :: tail => if (p(head)) Some(head) else tail.find(p)

  def filter(p: A => Boolean): Lixt[A] =
    this match
      case Nil => Nil
      case head :: tail => if (p(head)) head :: tail.filter(p) else tail.filter(p)

  def filterNot(p: A => Boolean): Lixt[A] =
    this match
      case Nil => Nil
      case head :: tail => if (!p(head)) head :: tail.filterNot(p) else tail.filterNot(p)


object Lixt:

  def empty[A]: Lixt[A] = Nil

  def apply[A](xs: A*): Lixt[A] = xs.foldRight(empty)((a, b) => a :: b)

export Lixt.*
