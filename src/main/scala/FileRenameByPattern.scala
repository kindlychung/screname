import java.io.File

/**
 * Created by IDEA on 17/08/15.
 */

class FileRenameByPattern(val oldFileNames: List[String], val searchPattern: String, val replacePattern: String, val replaceAll: Boolean) {
  val newFileNames = getNewFileNames()

  def getNewFileNames(): List[String] = {
    oldFileNames map {
      (x: String) =>
        if (replaceAll)
          x.replaceAll(searchPattern, replacePattern)
        else
          x.replaceFirst(searchPattern, replacePattern)
    }
  }

  def getFileNamePairs() = {
    oldFileNames zip newFileNames
  }

  def rename(): List[Boolean] = {
    rename(getFileNamePairs())
  }

  def rename(pairs: List[(String, String)]): List[Boolean] = {
    pairs map {
      case (o, n) => new File(o) renameTo new File(n)
    }
  }

  def printFileNamePairs() = {
    val w1 = oldFileNames.map(_.length).max
    val w2 = newFileNames.map(_.length).max
    getFileNamePairs() map {
      case (s1, s2) => println("%%%ds %%%ds".format(w1, w2).format(s1, s2))
    }
  }
}


