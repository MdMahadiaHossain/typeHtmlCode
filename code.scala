

object EntryPoint {
  def main(args: Array[String]): Unit = {
    implicit def processor[T <: {def execute()}](obj: T): html = {
      obj.execute()
      new html {}
    }

     a { $ =>$.href("www.facebook.com");$._class(Seq("container"));new text("hi my boy")}

  }
}

class a {
  def execute(): Unit = {
    println("processing. . . . scala code to html")
    println(s"<a href='$url' class='${_class}'>" + "click" + "</a>")
  }

  private var url = "#"
  private var _class: Seq[String] = Seq()


  def href(urla: String): a = {
    this.url = urla
    this
  }

  def _class(classes: Seq[String]): a = {
    this._class = classes
    this
  }
}

object a {
  def apply(f: a => html)(implicit processor: a => html): a = {

    val x = new a()
    f(x)
    processor(x)
    x
  }
}

class text(txt: String) extends html
