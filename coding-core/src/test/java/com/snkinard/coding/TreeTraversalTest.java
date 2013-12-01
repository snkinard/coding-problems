package com.snkinard.coding;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TreeTraversalTest {

    BinaryTree binaryTree;
    KnaryTree knaryTree;

    @BeforeTest
    public void setup() {
        BinaryTree c = new BinaryTree('c');
        BinaryTree e = new BinaryTree('e');
        BinaryTree d = new BinaryTree('d', c, e);
        BinaryTree a = new BinaryTree('a');
        BinaryTree b = new BinaryTree('b', a, d);
        BinaryTree h = new BinaryTree('h');
        BinaryTree i = new BinaryTree('i', h, null);
        BinaryTree g = new BinaryTree('g', null, i);
        binaryTree = new BinaryTree('f', b, g);

        knaryTree = new KnaryTree('a');
        KnaryTree kB = new KnaryTree('b');
        KnaryTree kC = new KnaryTree('c');
        knaryTree.addChild(kB);
        knaryTree.addChild(kC);
        KnaryTree kD = new KnaryTree('d');
        KnaryTree kE = new KnaryTree('e');
        KnaryTree kF = new KnaryTree('f');
        kB.addChild(kD);
        kB.addChild(kE);
        kB.addChild(kF);
        KnaryTree kG = new KnaryTree('g');
        kC.addChild(kG);
        KnaryTree kH = new KnaryTree('h');
        KnaryTree kI = new KnaryTree('i');
        kD.addChild(kH);
        kD.addChild(kI);
        KnaryTree kJ = new KnaryTree('j');
        kG.addChild(kJ);
    }

    @AfterTest
    public void teardown() {
        binaryTree = null;
        knaryTree = null;
    }

    @Test
    public void testPreorderRecursive()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "fbadcegih", TreeTraversal.preorderRecursive(binaryTree));
    }

    @Test
    public void testPreorderIterative()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "f b a d c e g i h", TreeTraversal.preorderIterative(binaryTree));
    }

    @Test
    public void testInorder()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "a b c d e f g h i", TreeTraversal.inorder(binaryTree));
    }

    @Test
    public void testPostorder()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "a c e d b h i g f", TreeTraversal.postorder(binaryTree));
    }

    @Test
    public void testLevelorder()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "f \nb g \na d i \nc e h", TreeTraversal.levelorderNewline(binaryTree));
    }

    @Test
    public void testLevelorderKnary()
            throws Exception {
        AssertJUnit.assertEquals("Broke-ass shit!!", "a \nb c \nd e f g \nh i j", TreeTraversal.levelorderNewline(knaryTree));
    }
}
