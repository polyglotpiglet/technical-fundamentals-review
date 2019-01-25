package com.ojha.algorithms.sorting

object QuickSort {

  def quickSort(arr: Array[Int]): Array[Int] = {
    val copy = arr.clone()
    go(copy, 0, copy.length - 1)
    copy
  }

  private def go(a: Array[Int], l: Int, r: Int): Unit = {
    if (l < r) {
      val p = partition(a, l, r)
      go(a, l, p - 1)
      go(a, p + 1, r)
    }
  }

  private def partition(a: Array[Int], l: Int, r: Int): Int = {
    val p = a(r)
    var i = l
    for (j <- l to r) {
      if (a(j) < p) {
        swap(a, i, j)
        i += 1
      }
    }
    swap(a, i, r)
    i
  }

  private def swap(a: Array[Int], i: Int, j: Int): Unit = {
    val t = a(i); a(i) = a(j); a(j) = t
  }

}
