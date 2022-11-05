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

object Lixt:

  def empty[A]: Lixt[A] = Nil

  def apply[A](xs: A*): Lixt[A] = xs.foldRight(empty)((a, b) => a :: b)

export Lixt.*
