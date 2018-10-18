import scala.swing._
import scala.swing.event._

object main extends SimpleSwingApplication {
  def top = new MainFrame {
    title = "Click Counter"
    val buttonAdd = new Button {
      text = "+1"
    }
    val buttonSubstract = new Button {
      text = "-1"
    }
    val label = new Label {
      text = "No button clicks registered"
    }
    contents = new BoxPanel(Orientation.Horizontal) {
      /*
       * Adding buttons to the contents
       */
      contents += buttonAdd
      contents += buttonSubstract
      contents += label
      border = Swing.EmptyBorder(100, 100, 100, 300)
    }
    /*
     * Listening to the buttons
     */
    listenTo(buttonAdd)
    listenTo(buttonSubstract)
    var nClicks: Int = 0
    /*
     * Reacting to clicked buttons
     */
    reactions += {
      case ButtonClicked(abstractButton) =>
        if (abstractButton == buttonAdd)
          nClicks += 1
        if (abstractButton == buttonSubstract)
          nClicks -= 1
        label.text = "Number of button clicks: " + nClicks
    }
  }
}