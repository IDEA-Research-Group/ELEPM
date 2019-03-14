package es.us.idea.elepm

import es.us.idea.adt.data.chameleon.internal.dtfs._

object Utility {
  def main(args: Array[String]) = {

    import es.us.idea.adt.data.chameleon.dsl.implicits._
    import es.us.idea.ele.xes.dsl.implicits._
    import pm.functions._

    val xlog =
      extract(
        define trace id("accode"),
        define trace event(
          activity = "workstation",
          criteria = orderBy(t"start_date" -> toDate("MM/dd/yyyy HH:mm:ss")),
          timestamp = t"start_date" -> toDate("MM/dd/yyyy HH:mm:ss")
        )
      ) from "datasets/aircraft_dataset_anonymized.json"

    // Let's save this xes file
    xlog save "output/xlog.xes"

    // Let's get the petrinet
    val petri = petrinet(xlog)

    petri save("output/petrinet.txt")
    petri show
  }
}
