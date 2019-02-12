import static org.junit.Assert.*;

import org.junit.Test;


public class BSTTest {

	@Test
	public void testLeafCount() {
		BST tree = new BST();
		assertTrue("The number of leafs should be 0", tree.leafCount() == 0);
		tree.add("b");
		assertTrue("The number of leafs should be 1", tree.leafCount() == 1);
		tree.add("a");
		tree.add("e");
		tree.add("f");
		tree.add("g");
		assertTrue("The number of leafs should be 2", tree.leafCount() == 2);
		tree.add("c");
		/*
		 *   b
		 *  / \
		 * a   e
		 *    / \
		 *   c   f
		 *        \
		 *         g
		 */
		assertTrue("The number of leafs should be 3", tree.leafCount() == 3);
	}
	
	@Test
	public void testNodeCount() {
		BST tree = new BST();
		assertTrue("The number of nodes should be 0", tree.nodeCount() == 0);
		tree.add("b");
		assertTrue("The number of nodes should be 1", tree.nodeCount() == 1);
		tree.add("a");
		tree.add("e");
		tree.add("f");
		tree.add("g");
		assertTrue("The number of nodes should be 5", tree.nodeCount() == 5);
		tree.add("c");
		/*
		 *   b
		 *  / \
		 * a   e
		 *    / \
		 *   c   f
		 *        \
		 *         g
		 */
		assertTrue("The number of nodes should be 6", tree.nodeCount() == 6);
	}
	
	
}
