package com.ojha.algorithms.graphs

import org.scalatest.FlatSpec

class GraphSpec extends FlatSpec {

  private val s = Node("s")
  private val a = Node("a")
  private val b = Node("b")
  private val c = Node("c")
  private val d = Node("d")
  private val e = Node("e")

  private val sa = Edge(s, a)
  private val sb = Edge(s, b)
  private val ac = Edge(a, c)
  private val bc = Edge(b, c)
  private val bd = Edge(b, d)
  private val cd = Edge(c, d)
  private val ce = Edge(c, e)
  private val de = Edge(d, e)
  private val graph = Graph(Seq(s, a, b, c, d, e), Seq(sa, sb, ac, bc, bd, cd, ce, de))


  // Q1
  "Graphs" should "do a BFS" in {
    assert(graph.bfs(s) == Seq(s, a, b, c, d, e).map(node => node.value))
  }

  // Q2
  "Graphs" should "do a DFS" in {
    assert(graph.dfs(s) == Seq(s, b, d, e, c, a).map(node => node.value))
  }


}
