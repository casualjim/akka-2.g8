package $package$

import akka.kernel.Bootable
import akka.actor._

/**
 * To use the microkernel at least one 'boot class' needs to be specified.
 * A boot class implements this interface ([[akka.kernel.Bootable]]) and
 * must have an empty default constructor.
 *
 * ActorSystems can be created within the boot class.
 *
 * An example of a simple boot class:
 * {{{
 * class BootApp extends Bootable {
 *   val system = ActorSystem("app")
 *
 *   def startup = {
 *     system.actorOf(Props[FirstActor]) ! FirstMessage
 *   }
 *
 *   def shutdown = {
 *     system.shutdown()
 *   }
 * }
 * }}}
 *
 * Boot classes are specified as main arguments to the microkernel.
 *
 * For example, using the akka script an application can be started with
 * the following at the command line:
 * {{{
 * bin/akka org.app.BootApp
 * }}}
 */
class Boot extends Bootable {
  
  val system = ActorSystem("$name$")

  /**
   * Callback run on microkernel startup.
   * Create initial actors and messages here.
   */
  def startup() {
    
  }

  /**
   * Callback run on microkernel shutdown.
   * Shutdown actor systems here.
   */
  def shutdown() {
    
  }

}