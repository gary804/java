public class BinaryTree {
	// Root node pointer. Will be null for an empty tree.
	private Node root;
/**
--Node--
The binary tree is built using this nested node class.
Each node stores one data element, and has left and right
sub-tree pointer which may be null.
The node is a "dumb" nested class -- we just use it for
storage; it does not have any methods.
*/
	private static class Node {
		Node left;
		Node right;
		int data;
		Node(int newData){
			left = null;
			right = null;
			data = newData;
		}
	}
	public BinaryTree(){
		root = null;
	}

/**
Returns true if the given target is in the binary tree.
Uses a recursive helper.
*/
	public boolean lookup(int data) {
		return(lookup(root, data));
	}
	private	boolean lookup(Node root, int data){
		if (root == null){
			return false;
		}
		if (root.data==data) {
			return true;
		} else if (root.data<=data){
			return lookup(root.left, data);
		} else {
			return lookup(root.right, data);
		}
		
	}

/**
Inserts the given data into the binary tree.
Uses a recursive helper.
*/
	public void insert(int data) {
		root = insert(root, data);
	}
	private Node insert (Node node, int data){
		if (node==null) {
			node = new Node(data);
		} else if (data <= node.data) {
			node.left = insert (node.left, data);
		} else {
			node.right = insert (node.right, data);
		}
		return node; // in any case, return the new pointer to the caller
	}

/**
Build 123 by calling insert() three times.
Note that the '2' must be inserted first.
*/
	public void build123() {
		root = insert(root, 2);
		root = insert(root, 1);
		root = insert(root, 3);
	}

/**
Returns the number of nodes in the tree.
Uses a recursive helper that recurs
down the tree and counts the nodes.
*/	
	public int size() {	//counts the nodes
		return(size(root));
	}
	private int size(Node node){
		if (node == null) return 0;
		return 1 + size(node.left) + size(node.right);
	}

/**
Returns the max root-to-leaf depth of the tree.
Uses a recursive helper that recurs down to find
the max depth.
*/
	public int maxDepth() {
		return(maxDepth(root));
	}
	private int maxDepth(Node node){
		if(node==null) return 0;
		return (maxDepth(node.left)>=maxDepth(node.right))?1+maxDepth(node.left):1+maxDepth(node.right);
	}

/**
Returns the min value in a non-empty binary search tree.
Uses a helper method that iterates to the left to find
the min value.
*/
	public int minValue() {
		return( minValue(root) );
	}
	private	int minValue(Node node){
		//works on recursive
		//if(node.left==null) return node.data;
		//return minValue(node.left);
		while(node.left!=null){
			node=node.left;
		}
		return node.data;
	}
	public int maxValue() {
		return( maxValue(root) );
	}
	private	int maxValue(Node node){
		//works on recursive
		//if(node.right==null) return node.data;
		//return maxValue(node.right);
		while(node.right!=null){
			node=node.right;
		}
		return node.data;
	}

/**
Prints the node values in the "inorder" order.
Uses a recursive helper to do the traversal.
*/
	public void printTree() {
		printTree(root);
		System.out.println("\b ");
	}
	private void printTree(Node node){
		if (node==null) return;
		printTree(node.left);
		System.out.print(node.data+",");
		printTree(node.right);
	}

/**
Prints the node values in the "postorder" order.
Uses a recursive helper to do the traversal.
*/
	public void printPostorder() {
		printPostorder(root);
		System.out.println("\b ");
	}
	private	void printPostorder(Node node){
		if (node==null) return;
		printPostorder(node.left);
		printPostorder(node.right);
		System.out.print(node.data + ",");
	}

/**
Given a tree and a sum, returns true if there is a path from the root
down to a leaf, such that adding up all the values along the path
equals the given sum.
Strategy: subtract the node value from the sum when recurring down,
and check to see if the sum is 0 when you run out of tree.
*/
	public boolean hasPathSum(int sum) {
		return( hasPathSum(root, sum) );
	}
	private boolean hasPathSum(Node node, int sum){
		if (node==null){
			if (sum==0) return true;
			else return false;
		}
		sum -= node.data;
		return (hasPathSum(node.left, sum)||hasPathSum(node.right, sum));
	}

/**
Given a binary tree, prints out all of its root-to-leaf
paths, one per line. Uses a recursive helper to do the work.
*/
	public void printPaths() {
		int[] path = new int[1000];
		if (root!=null) printPaths(root, path, 0);
	}
	private void printPaths(Node node, int[] path, int len){
		path[len++] = node.data;
		if (node.left == null && node.right == null){
			for (int i=0; i<len; ++i){
				System.out.print(path[i]+",");
			}
			System.out.println("\b ");
		} else {
			if(node.left!=null){
				printPaths(node.left, path, len);
			}
			if(node.right!=null){
				printPaths(node.right, path, len);
			}
		}

	}

	public void mirror() {
		mirror(root);
	}
	private void mirror(Node node){
		if (node==null){
			return;
		}
		mirror(node.left);
		mirror(node.right);
		Node temp =node.left;
		node.left = node.right;
		node.right = temp;
	}

	public void doubleTree() {
		doubleTree(root);
	}
	private void doubleTree(Node node){
		if (node==null) return;
		doubleTree(node.left);
		doubleTree(node.right);
		Node temp = new Node(node.data);
		temp.left = node.left;
		node.left = temp;

	}
/**
Compares the receiver to another tree to
see if they are structurally identical.
*/
	public boolean sameTree(BinaryTree other) {
		return( sameTree(root, other.root) );
	}
	private boolean sameTree(Node node1, Node node2){
		if (node1==null&&node2==null) return true;
		if (node1.data!=node2.data) return false;
		return (sameTree(node1.left, node2.left)&&sameTree(node1.right, node2.right));
	}

/**
For the key values 1...numKeys, how many structurally unique
binary search trees are possible that store those keys?
Strategy: consider that each value could be the root.
Recursively find the size of the left and right subtrees.
*/
	public static int countTrees(int numKeys) {
		if (numKeys <=1) {
			return(1);
		} else {
			int sum = 0;
			int left, right;
			for (int i=1; i<=numKeys; ++i){
				left = countTrees(i-1);	//left side subtrees
				right = countTrees(numKeys-i);	//right side subtrees
				sum += left*right;
			}
			return sum;
		}
	}

/**
Tests if a tree meets the conditions to be a
binary search tree (BST).
*/
	public boolean isBST() {
		//return(isBST(root)); //works
		return( isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE) ); //works
	}
	private boolean isBST(Node node){
		if (node==null) return true;
		if (node.left==null&&node.right==null){
			return true;
		} else {
			if (node.left!=null){
				if (node.data<node.left.data) return false;
			}
			if (node.right!=null){
				if (node.data>=node.right.data) return false;
			}
			return (isBST(node.left)&&isBST(node.right));
		}
	}
	private boolean isBST(Node node, int min, int max){
		if (node==null) return true;
		if (node.data < min) {
			//System.out.println("node.data < min: data="+node.data+", min="+min );
			return false;
		}
		if (node.data > max) {
			//System.out.println("node.data > max: data="+node.data+", max="+max );
			return false;
		}
		return ( isBST(node.left, min, node.data) && isBST(node.right, node.data+1, max) );
//		boolean l = isBST(node.left, min, node.data);
//		if(!l) System.out.println("node.data ="+node.data+", left is not BST");
//		boolean r = isBST(node.right, node.data+1, max);
//		if(!r) System.out.println("node.data ="+node.data+", right is not BST");
//		return (l&&r);
	}

	public void treeToList(){
		root = treeToList(root);
	}
	private Node treeToList(Node node){
		if (node==null){
			return null;
		} else {
			Node leftList = treeToList(node.left);
			Node rightList = treeToList(node.right);
			//make node a single node list
			node.left = node;
			node.right = node;
			node = append(leftList, node);
			node =append(node, rightList);
			return node;
		}
	}
	private void join(Node a, Node b){ //join two nodes
		a.right = b; //right=next
		b.left = a; //left=previose
	}
	private Node append(Node a, Node b){ //a, b are two lists
		if (a==null) return b;
		if (b==null) return a;
		Node aLast = a.left;
		Node bLast = b.left;
		join(aLast, b);
		join(bLast, a);
		return a;
	}
	public void printList(){
		printList(root);
	}
	private void printList(Node node){
		if (node==null) return;
		Node head = node;
		do {
			System.out.print(node.data+",");
			node=node.right;
		}while(node!=head);
		System.out.println("\b ");
	}

	public static void main(String[] args){
		BinaryTree binarytree = new BinaryTree();
		binarytree.build123();
		System.out.println("The tree size is " + binarytree.size() +".");
		System.out.println("The tree max depth is " + binarytree.maxDepth() +".");
		System.out.println("The tree minValue is " + binarytree.minValue() +".");
		System.out.println("The tree inorder is:");
		binarytree.printTree();
		System.out.println("The tree postorder is:");
		binarytree.printPostorder();
		System.out.println("Does the tree has pathSum 3? " + binarytree.hasPathSum(3) +".");
		System.out.println("Does the tree has pathSum 4? " + binarytree.hasPathSum(4) +".");
		System.out.println("Does the tree has pathSum 5? " + binarytree.hasPathSum(5) +".");
		System.out.println("The tree paths are:");
		binarytree.printPaths();
		System.out.println("The mirrored tree is:");
		binarytree.mirror();
		binarytree.printTree();
		System.out.println("Is the trees BST? "+binarytree.isBST());

		System.out.println("The tree is mirrored again:");
		binarytree.mirror();	//restore the tree
		BinaryTree binarytree2 = new BinaryTree();
		binarytree2.build123();
		System.out.println("Are the two trees same? "+binarytree.sameTree(binarytree2));
		System.out.println("Is the trees BST? "+binarytree.isBST());

		System.out.println("The double tree is:");
		binarytree.doubleTree();
		binarytree.printTree();
		System.out.println("Is the trees BST? "+binarytree.isBST());

		System.out.println("Conver a trees to a list:");
		binarytree.treeToList();
		binarytree.printList();

		System.out.println("4 keys, how many can different trees be constructed? "+binarytree.countTrees(4));
	}
}
