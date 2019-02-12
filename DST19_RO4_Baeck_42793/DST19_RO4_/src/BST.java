public class BST {

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
	   
	   private boolean findNode(String searchKey) // auxiliary method for the class
	   function: find the node that contains searchKey
	   input: searchKey
	   precondition: the tree has been created, no duplicate keys allowed
	   output: true if searchKey is found in the tree, false otherwise
	   postcondition: true if searchKey is found in the tree, false otherwise
	   				  if true, curr points to the node and parent to its parent
	   				  if false, curr is null and parent points to the logical parent
	   				  of a node with searchKey
	   				  
	   	public NodeInfo retrieveNode(String searchKey)
	   	function: retrieves the information part of the node containing searchKey
	   	input: searchKey
	   	precondition: the tree has been created, no duplicate keys allowed
	   	output: the information part of the node containing searchKey, if found, 
	   			null otherwise
	   	postcondition: returns the information part of the node containing searchKey
	   			if searchKey found in the tree, returns null otherwise
	   			
	   	public void add(String keyIn)
	   	function: a node with info part containing keyIn is created and inserted in the tree
	   			if the key is not found there alredy
	   	input: the key to be inserted
	   	precondition: the tree has been created
	   	output:
	   	postcondition: a node containing keyIn has been created and inserted in the tree.
				curr points to this new node and parent to its logical parent. If a node 
				with keyIn was found in the tree, the tree is left unchanged. curr points to 
				this node and parent to its parent.
				
		public void deleteNode(String searchKey)
		function: delete the node containing searchKey
		input: the key for the node to be deleted
		precondition: the tree has been created, no duplicate keys allowed
		output:
		postcondition: the node containing searchKey has been deleted from the tree. curr is 			
				set to null. parent points to the parent of the deleted node. If the node to
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
	
	public BST () {
		tree = null;	// the root
		parent = null;  // keeps track of the parent of the current node (curr)
		curr = null;    // help pointer to find a node or its place in the tree
	}
	
	public boolean isEmpty() {
		return tree == null;
	}
	
	private boolean findNode(String searchKey) { // help method to find a node
	                                             // or its insertion place
		curr = tree;
		parent = null;
		if (curr == null) return false; // empty tree
		while ((curr != null) && !(curr.info.key.equals(searchKey))) { 
			// have not found the node with key - yet ...
			if (searchKey.compareTo(curr.info.key) < 0) { // key less than current info
				parent = curr;    // update parent: next generation
				curr = curr.left; // search the left subtree
			}
			else { // key is greater than current info
				parent = curr; 
				curr = curr.right; // search the right subtree
			}
		}
	    // if curr equals null, the node was not found.
	    // parent points to its logical parent in the tree.
	    // otherwise curr points to the node with key and parent to its parent 
	
	    if (curr == null) return false;
	    else return true;
	    
	}
	
	public NodeInfo retrieveNode(String searchKey) { 
		// retrieve the info part of the node with key if it is in the tree
	    if (findNode(searchKey)) return curr.info;
	    else return null;
	   
	}                                     
	
	public void add(String keyIn) {
		
		boolean found = findNode(keyIn);
		if (found) { // the key is already present in the tree!
	 		System.out.println("The key is already present, duplicates not allowed!");
		}
		else { // insert the new node as a child to parent
			NodeInfo newInfo = new NodeInfo(keyIn); // create the info part
			BSTNode newNode = new BSTNode(newInfo); // creates a BSTNode
	        if (parent == null)  // a new root (the first node)
				tree = newNode;
			else { // insert in an existing tree
				if (keyIn.compareTo(parent.info.key) < 0) // left child
					parent.left = newNode;
				else  // right child
					parent.right = newNode;
			}
			curr = newNode;
		}
	}
	
	public void deleteNode(String searchKey) { // deletes the node with the given key
	
		boolean found = findNode(searchKey);
		
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
					if (parent == null)  // delete a root with one child
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
	// of the tree (the variable called tree).
				
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
	
	public static void main(String[] args) {
		BST bst = new BST();
		//bst.add("root");
		//bst.add("moot");
		//bst.add("boot");
		//bst.add("woot");
		//bst.add("zoot");
		//bst.add("voot");
		//bst.inOrder(bst.tree);
		System.out.println(bst.leafCount());
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
		BSTNode(NodeInfo dataIn, BSTNode l, BSTNode r) { // for test constructions
			info = dataIn;
			left = l;
			right = r;
		}
		*/
	}

}
