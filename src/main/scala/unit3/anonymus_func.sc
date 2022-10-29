def sum(a: Int, b: Int) = a + b

val j = { (a: Int, b: Int) => a + b }
val j2: (Int, Int) => Int = { (a, b) => a + b }
val j3: (Int, Int) => Int = _ + _

def subtract(a: Int, b: Int) = a - b

subtract(4, 3)

def both(f: (Int, Int) => Int) = f

val j4: Int = j3(2, 6)
j4
both(j2)

def hello(s: String): String = s"Hello, $s"

hello("Jim")
val s = { (s: String) => s"Hello, $s" }

s("Propper")

def multiply(s: String, n: Int): Int = s.length * n
def toDouble(n: Int): Double = n.toDouble
def concat(s1: String, s2: String): String = s1 + s2
def inverseConcat(s1: String, s2: String): String = s2 + s1
def myLongString(s: String): String = {
  val length = s.length
  s.reverse * length
}

val multiply2: (String, Int) => Int = { _.length * _ }
multiply("check", 2)
multiply2("check", 2)

val toDouble2: Int => Double = _.toDouble
toDouble(5)
toDouble2(5)

val concat2: (String, String) => String = _ + _
concat("one", "two")
concat2("one", "two")

val inverseConcat2: (String, String) => String = { (s1: String, s2: String) => s2 + s1 }
inverseConcat("former", "later")
inverseConcat2("former", "later")

val myLongString2: String => String = { (s: String) => { s.reverse * s.length } }
myLongString("che")
myLongString2("che")