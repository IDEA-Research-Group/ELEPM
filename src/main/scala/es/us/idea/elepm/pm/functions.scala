package es.us.idea.elepm.pm

import es.us.idea.pm.Methods
import org.deckfour.xes.model.XLog
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.processmining.models.graphbased.AbstractGraphElement
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetImpl

object functions {

  def petrinet(xlog: XLog) = Methods.getPetriNet(xlog)
  def graph(petrinet: PetrinetImpl) = Methods.getPetriNetGraph(petrinet)

  implicit class PetrinetImplicit(petrinetImpl: PetrinetImpl) {
    def graph = Methods.getPetriNetGraph(petrinetImpl)
    def show = Methods.showGraph(Methods.getPetriNetGraph(petrinetImpl))
    def save(filePath: String) = Methods.savePetrinet(petrinetImpl, filePath)
  }

  implicit class GraphImplicit(graph: DefaultDirectedGraph[AbstractGraphElement, DefaultEdge]) {
    def show = Methods.showGraph(graph)
    def save(filePath: String) = Methods.saveGraph(graph, filePath)
  }

}
