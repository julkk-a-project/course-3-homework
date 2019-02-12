public class BSTrec {

	/* Annamari Soini, Linda Mannila 27.09.2012
	
	   Greetings from the compiler:
	   "BST is not abstract and does not override abstract method 
	   compareTo(java.lang.Object) in java.lang.Comparable
	   public class BST implements Comparable {	"
	   
	   methods:
	   
	   public boolean isEmpty() 
	   function: checks for an empty tree
	   input: none
	   precondition: the tree has been created
	   output: true if the tree is empty, false otherwise
	   postcondition: true if the tree is empty, false otherwise
	   
	   private boolean findNodeRec(String searchKey, BSTNode subtree, BSTNode subparent)
	   function: find the node that contains searchKey
	   input: searchKey, the (sub)tree that is searched (recursion parameter), the parent 
	   		  of this (sub)tree (recursion parameter)
	   precondition: the tree has been created, no duplicate keys allowed
	   output: true if searchKey is found in the tree, false otherwise
	   postcondition: true if searchKey is found in the tree, false otherwise
	   				  if true, curr points to the node and parent to its parent
	   				  if false, curr is null and parent points to the logical parent
	   				  of a node with searchKey
	   	NB!! A search should always start with findNodeRec(<key>, tree, null) as the
	   	value of the class variable parent is dependent on previous method calls!
	   				  
	   	public NodeInfo retrieveNode(String searchKey)
	   	function: retrieves the information part of the node containing searchKey
	   	input: searchKey
	   	precondition: the tree has been created, no duplicate keys allowed
	   	output: the information part of the node containing searchKey, if found, 
	   			null otherwise
	   	postcondition: returns the information part of the node containing searchKey
	   			if searchKey found in the tree, returns null otherwise
	   			
	   	public void addRec(String keyIn, BSTNode subtree, BSTNode subparent, boolean goLeft)
	   	// recursive!
	   	function: a node with info part containing keyIn is created and inserted in the tree
	   	input: the key to be inserted, the root of the (sub)tree where insertion is to take 		   place (recursion parameter), the parent of the root of this subtree 
	   		   (recursion parameter), goLeft (true/false) to choose the right subtree
	    precondition: the tree has been created. A node with keyIn is not already present.
	   	output:
	   	postcondition: a node containing keyIn has been created and inserted in the tree.
			   curr points to this new node and parent to its parent. 
							
		public void deleteNode(String searchKey) // iterative
		function: delete the node containing searchKey
		input: the key for the node to be deleted
		precondition: the tree has been created, no duplicate keys allowed
		output:
		postcondition: the node containing searchKey has been deleted from the tree. curr is 			set to null. parent points to the parent of the deleted node. If the node to
				be deleted was not found in the tree, curr is null and parent points to the
				logical parent of a node with searchKey.
				
		public void inOrder(BSTNode root) // recursive
		function: traverse the tree in inOrder (sorted in ascending order)
		input: the root of the tree to be traversed
		precondition: the tree has been created
		output: the key parts of the information parts, in order, to standard output
		postcondition: the keys are printed in (ascending) order
		
		public void preOrder(BSTNode root) // recursive
		function: traverse the tree in preOrder (node first, then its children)
		input: the root of the tree to be traversed
		precondition: the tree has been created
		output: the key parts of the information parts, in preorder, favoring left, to 	
				standard output
		postcondition: the keys are printed in preorder, favoring left, to 	
				standard output
				
		public void postOrder(BSTNode root) // recursive
		function: traverse the tree in postOrder (children first, node last)
		input: the root of the tree to be traversed
		precondition: the tree has been created
		output: the key parts of the information parts, in postorder, favoring left, to 	
				standard output
		postcondition: the keys are printed in postorder, favoring left, to 	
				standard output
	
			
	*/			 
	
	BSTNode tree, parent, curr;
	
	public BSTrec () {
		tree = null;    // the root of the tree
		parent = null;  // keeps track of the parent of the current node
		curr = null;    // help pointer to find a node or its place in the tree
	}
	
	public boolean isEmpty() {
		return tree == null;
	}
	
	private boolean findNodeRec(String searchKey, BSTNode subtree, BSTNode subparent) { 
		
		// recursive help method to find a node or its insertion place
		
		if (subtree == null) { // base case 1: node not found
			curr = null;
			parent = subparent; // the logical parent for the value
			return false;
		}
		else { // we are still in the tree
			if (subtree.info.key.equals(searchKey)) { // base case 2: found the node
				curr = subtree;     // update current to point to the node
				parent = subparent; // update parent to point to its parent
				return true;
			}
			else { // must choose the right subtree to search
				if (searchKey.compareTo(subtree.info.key) < 0) { 
					// key less than current info: search the left subtree!
					return findNodeRec(searchKey, subtree.left, subtree);
				}
				else { // key greater than current info: search the right subtree!
					return findNodeRec(searchKey, subtree.right, subtree);	
				}
			}
		}
	}
	
	
	public NodeInfo retrieveNode(String searchKey) { 
		// retrieve the info part of the node with key
		// if it is in the tree
	    if (findNodeRec(searchKey, tree, null)) return curr.info;
	    else return null;
	   
	}                                     
	
	public void addRec(String keyIn, BSTNode subtree, BSTNode subparent, boolean goLeft) {
		
		// recursive add - the node is inserted into a subtree, as a new root for 
		// a new subtree. This can be done elegantly in languages that have
		// call by reference-parameters, by modifying the link in the parent-to-be.
		// However, this cannot be done in java. So subtree will go through the tree
		// until it is null, and parent will at that moment point to the parent-to-be.
		// The new node is created and linked by the constructor as a left or right
		// child for this parent, as dictated by the goLeft-parameter (true/false).
		// This method does not check that the node is not already in the tree; this 
		// must be tested before the insertion.
		
		if (tree == null) { // a first node will be the new root: base case 1
			tree = new BSTNode(new NodeInfo(keyIn));
			curr = tree;
			parent = null;
		} // the tree was initiated, we got a new root
		else { // insertion in an existing tree
			if (subtree == null) { // we found the insertion place: base case 2
				if (goLeft) { // the new node is to be a left child
					subparent.left = new BSTNode(new NodeInfo(keyIn));
					curr = subparent.left;
					parent = subparent; 
				}
				else { // the new node is to be a left child
					subparent.right = new BSTNode(new NodeInfo(keyIn));
					curr = subparent.right;
					parent = subparent;
				}
			}
			else { // still searching for the insertion place: left or right subtree?
				if (keyIn.compareTo(subtree.info.key) < 0) {
					addRec(keyIn, subtree.left, subtree, true);
					// the node must be inserted in the left subtree of the
					// current node: a recursive call
				}
				else { 
					addRec(keyIn, subtree.right, subtree, false);
					// the node must be inserted in the right subtree of the
					// current node: a recursive call
				}
			}
		}
	}
	
	public void deleteNode(String searchKey) { 
		
		// deletes the node with the given key, uses a recursive search
	
		boolean found = findNodeRec(searchKey, tree, null);
		
		if (!found) // the key is not in the tree
			System.out.println("The key is not in the tree!");
		else { // curr points to the node to be deleted, parent to its parent
			if ((curr.left == null) && (curr.right == null)) // delete a leaf
				if (parent == null)  // delete the last node
					tree = null;
				else // the leaf to be deleted is not the root   
					if (curr == parent.left) // delete a left child
						parent.left = null;
					else 					 // delete a right child
						parent.right = null;
			else // delete a node with children, one or two
				if ((curr.left != null) && (curr.right != null)) { // two children
					BSTNode surrogateParent = curr;
					BSTNode replacement = curr.left;
					while (replacement.right != null) {
						surrogateParent = replacement;
						replacement = replacement.right;
					}
				// the greatest value of the left subtree is chosen as a replacement
					curr.info = replacement.info; // the information is copied over
				// replacement must now be deleted. It has no children, or a left child.
					if (curr == surrogateParent) {
					    // there was no right path in the left subtree
						curr.left = replacement.left; // curr "adopts" the left
					    // child, if any, of the replacement. The replacement is jumped over.
						replacement = null;
					}
					else  { // there was a right path in the left subtree
						surrogateParent.right = replacement.left;
						// replacement, the right child of its parent, is jumped over.
						// The parent "adopts" its left child, if any.
						replacement = null;
					}
				} // End: if two children
				else { // delete a node with one child
					if (parent == null)   // delete a root with one child
						if (curr.left != null)  // there is a left child
							tree = curr.left;	// update root
						else 					// there is a right child
							tree = curr.right;	// update root
				    // end: the node to be deleted was a root with one child
					else  // delete a non-root node with one child
						if (curr == parent.left)    // delete a left child ...
							if (curr.right == null) // ... with a left child
								parent.left = curr.left; 
								// the parent "adopts" the grandchild			
							else 					// ... with a right child
								parent.left = curr.right; 
								// The parent "adopts" the grandchild 
						else // delete a right child ...
							if (curr.right == null) // ... with a left child
								parent.right = curr.left; 
								// the parent "adopts" the grandchild
							else                    // ... with a right child
								parent.right = curr.right; 
								// The parent "adopts" the grandchild
				} // end: else a node with one child
				curr = null; // curr no longer points to the deleted node
		}
	} // method
	
	// The following routines must have a recursion parameter root.
	// The actual parameter sent to the original call should be the root
	// of the tree (the variable tree).
				
	public void inOrder(BSTNode root) {
		if (root != null) { // implicit base case: empty tree: do nothing
			inOrder(root.left); // process the left subtree
			System.out.println(root.info.key); // process the node itself
			inOrder(root.right); // process the right subtree
		}
	}
	
	public void preOrder(BSTNode root) {
		if (root != null) { // implicit base case: empty tree: do nothing
			System.out.println(root.info.key); // process the node itself
			preOrder(root.left);  // process the left subtree
			preOrder(root.right); // process the right subtree
		}
	}
	
	public void postOrder(BSTNode root) {
		if (root != null) { // implicit base case: empty tree: do nothing
			postOrder(root.left);  // process the left subtree
			postOrder(root.right); // process the right subtree
			System.out.println(root.info.key); // process the node itself
		}
	}
	
	public int nodeCount() {
		return -1;
	}
	
	public int leafCount() {
		return -1;
	}
	
	private class BSTNode {
		NodeInfo info;
		BSTNode left, right;
		
		/*
		BSTNode() {
			info = null;
			left = null;
			right = null;
		}
		*/
		
		BSTNode(NodeInfo dataIn) {
			info = dataIn;
			left = null;
			right = null;
		}
	
		/*
		BSTNode(NodeInfo dataIn, BSTNode l, BSTNode r) {
			info = dataIn;
			left = l;
			right = r;
		}
		*/
	}

}
