import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class BSTrecTest {

	@Test
	public void testLeafCount() {
		BSTrec tree = new BSTrec();
		assertTrue("The number of leafs should be 0", tree.leafCount() == 0);
		tree.addRec("b", tree.tree, tree.parent, true);
		assertTrue("The number of leafs should be 1", tree.leafCount() == 1);
		tree.addRec("a", tree.tree, tree.parent, true);
		tree.addRec("e", tree.tree, tree.parent, true);
		tree.addRec("f", tree.tree, tree.parent, true);
		tree.addRec("g", tree.tree, tree.parent, true);
		assertTrue("The number of leafs should be 2", tree.leafCount() == 2);
		tree.addRec("c", tree.tree, tree.parent, true);
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
		BSTrec tree = new BSTrec();
		assertTrue("The number of nodes should be 0", tree.nodeCount() == 0);
		tree.addRec("b", tree.tree, tree.parent, true);
		assertTrue("The number of nodes should be 1", tree.nodeCount() == 1);
		tree.addRec("a", tree.tree, tree.parent, true);
		tree.addRec("e", tree.tree, tree.parent, true);
		tree.addRec("f", tree.tree, tree.parent, true);
		tree.addRec("g", tree.tree, tree.parent, true);
		assertTrue("The number of nodes should be 5", tree.nodeCount() == 5);
		tree.addRec("c", tree.tree, tree.parent, true);
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
