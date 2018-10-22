package com.ojha.algorithms.sorting

import org.scalatest._

class QuickSortSpec extends FlatSpec {

  "Quick sort" should "sort elements in a non empty array" in {

    val arr = Array(4,1,3,2,7,5,6,8)
    val sorted = QuickSort.quickSort(arr)

    assert(sorted sameElements Array(1, 2, 3, 4, 5, 6, 7, 8))
  }

  "Quick sort" should "sort pair of elements" in {

    val arr = Array(2,1)
    val sorted = QuickSort.quickSort(arr)

    assert(sorted sameElements Array(1, 2))
  }

}
