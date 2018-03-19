/**
 * Created by IDEA on 10/09/2015.
 */
import org.scalatest._

class TestSpec extends FlatSpec with Matchers {
  "File rename" should "generate correct new filenames" in {
    val o1 = new FileRenameByPattern(List("ff1", "ff2"), "f", "file", true)
    o1.getNewFileNames() should be (List("filefile1", "filefile2"))
    val o2 = new FileRenameByPattern(List("f1", "f2"), "f", "file", false)
    o2.getNewFileNames() should be (List("file1", "file2"))
  }

  "Simple regex replacement" should "work" in {
    val o1 = new FileRenameByPattern(List("f11", "f22"), """f\d""", "file", true)
    o1.getNewFileNames() should be (List("file1", "file2"))
    val o2 = new FileRenameByPattern(List("f11", "f22"), """f(\d)""", "file$1", false)
    o2.getNewFileNames() should be (List("file11", "file22"))
  }
}
