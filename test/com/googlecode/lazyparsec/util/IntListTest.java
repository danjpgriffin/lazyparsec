package com.googlecode.lazyparsec.util;

import junit.framework.TestCase;

/**
 * Unit test for {@link IntList}.
 * 
 * @author Ben Yu
 */
public class IntListTest extends TestCase {
  
  public void testCalcSize() {
    assertEquals(4, IntList.calcSize(4, 1));
    assertEquals(3, IntList.calcSize(1, 3));
    assertEquals(3, IntList.calcSize(2, 3));
    assertEquals(3, IntList.calcSize(3, 3));
    assertEquals(6, IntList.calcSize(4, 3));
  }
  
  public void testConstructor() {
    IntList intList = new IntList();
    assertEquals(0, intList.size());
  }
  
  public void testConstructor_withCapacity() {
    IntList intList = new IntList(1);
    assertEquals(0, intList.size());
  }
  
  public void testToArray() {
    IntList intList = new IntList();
    assertEqualArray(intList.toArray());
    assertSame(intList, intList.add(1));
    assertSame(intList, intList.add(2));
    assertEqualArray(intList.toArray(), 1, 2);
  }
  
  public void testGet() {
    IntList intList = new IntList();
    assertEquals(0, intList.size());
    intList.add(1);
    assertEquals(1, intList.get(0));
    assertEquals(1, intList.size());
    assertEqualArray(intList.toArray(), 1);
  }
  
  public void testGet_throwsForNegativeIndex() {
    IntList intList = new IntList();
    try {
      intList.get(-1);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {}
  }
  
  public void testGet_throwsForIndexOutOfBounds() {
    IntList intList = new IntList();
    try {
      intList.get(0);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {}
  }
  
  public void testSet() {
    IntList intList = new IntList(0);
    intList.add(1);
    intList.set(0, 2);
    assertEquals(2, intList.get(0));
    assertEqualArray(intList.toArray(), 2);
  }
  
  public void testEnsureCapacity() {
    IntList intList = new IntList(0);
    intList.add(1);
    intList.ensureCapacity(100);
    assertEquals(1, intList.size());
    assertEqualArray(intList.toArray(), 1);
  }
  
  public void testSet_throwsForNegativeIndex() {
    IntList intList = new IntList();
    try {
      intList.set(-1, 0);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {}
  }
  
  public void testSet_throwsForIndexOutOfBounds() {
    IntList intList = new IntList();
    try {
      intList.set(0, 0);
      fail();
    } catch (ArrayIndexOutOfBoundsException e) {}
  }
  
  private void assertEqualArray(int[] array, int... values) {
    assertEquals(array.length, values.length);
    for (int i = 0; i < array.length; i++) {
      assertEquals(values[i], array[i]);
    }
  }
}