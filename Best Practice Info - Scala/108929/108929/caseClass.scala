 case class TestRouter(routees: Vector[ActorRefRoutee], resizer: Resizer)(implicit system: ActorSystem, timeout: Timeout) {
     system.registerOnTermination(close())
     var msgs: Set[TestLatch] = Set()
     def mockSend(l: TestLatch = TestLatch(),
                 routeeIdx: Int = Random.nextInt(routees.length),
                 wait: Boolean = true)(implicit sender: ActorRef): TestLatch = {
      val target = routees(routeeIdx)
      target.send(l, sender)
      msgs = msgs + l
      if (wait) waitForMessageToArrive()
      l
    }
}