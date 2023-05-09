
// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }
    private boolean check(BSTree root){
    if(root==null) return true;//Base case
    boolean r=check(root.right);
    boolean l=check(root.left);
    
    if(!l || !r) {
        return false;}
    if(root.left!=null){
        if(root.left.parent==root){
            if(root.left.key>root.key) return false;
            if(root.left.key==root.key){
                if(root.left.address>root.address) return false;
            }
        }
        else return false;
    }
    if(root.right!=null){
        if(root.right.parent==root){
            if(root.right.key<root.key) return false;
            if(root.right.key==root.key){
                if(root.right.address<root.address) return false;
            }
        }
        else return false;

    }

    return true;
    }
    
    public BSTree Insert(int address, int size, int key) 
    {   
        BSTree node =new BSTree(address,size,key);   
        BSTree sentinal=this;
        //if(sentinal==null) return null;
        
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null) {
                sentinal.right=node;
                node.parent=sentinal;
                return node;}//Tree is empty
            }
        else{
            while(sentinal.parent!=null)
                sentinal=sentinal.parent;
            
            }
            
                BSTree root=sentinal.right;
                BSTree p=root.parent;
                while(root!=null){
                    p=root;
                    if(node.key<root.key) {
                        
                        root=root.left;}
                    else if(node.key>root.key) {
                        
                        root=root.right;}
                    else {//Tie breaker keys are same
                        if(node.address<root.address) {
                            
                            root=root.left;}
                        else {
                            
                            root=root.right;}
                        }
                    }
                    if(node.key<p.key){
                    p.left=node; 
                    node.parent=p;
                    return node;}
                    else if(node.key>p.key){
                        p.right=node; 
                        node.parent=p;//check insertion at the end node which is null??
                        return node;
                    }
                    else{
                        if(node.address<p.address){
                            p.left=node; 
                            node.parent=p;
                            return node;
                        }
                        else{
                            p.right=node; 
                            node.parent=p;
                            return node;

                        }
                    }
             
      //return null;
     }

    public boolean Delete(Dictionary e)
    {   if(e==null) return false;
        BSTree sentinal=this;
           
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null) return false;//Tree is empty
        }
        else{
            while(sentinal.parent!=null)
                sentinal=sentinal.parent;
        }
                BSTree root=sentinal.right;
                boolean found=false;
                while(root!=null && found==false){
                    if(e.key<root.key) root=root.left;
                    else if(e.key>root.key) root=root.right;
                    else {//Tie breaker keys are same
                        if(e.address==root.address && e.size==root.size) found=true;
                        else if(e.address<root.address) root=root.left;
                        else root=root.right;
                        }
                    }
                if(root==null) return false;
                //check root has two child 
                if(root.left!=null && root.right!=null){
                    BSTree suc = root.right;
    		        while (suc.left != null) {
                       suc = suc.left;
    
    		            }
    		        int flag=0;
    		        if (root.parent.left == root) {
    			        flag=1;
    		            }
    		        int flag1=0;
    		        if (suc.parent.left == suc) {
    			        flag1=1;
    		        }
                    if(flag1==1) {
                        BSTree rp = root.parent;
                        BSTree rr = root.right;
                        
                         BSTree sucr = suc.right;
                        BSTree rl = root.left;
                        
                        BSTree sucp = suc.parent;
                        suc.parent = rp;
                        if (flag==0) {
                            rp.right = suc;
                            
                        } 
                        else {
                            rp.left = suc;
                        }
                        root.parent = sucp;
                        sucp.left = root;
                        root.left = null;
                        root.right = sucr;
                        
                        if (sucr != null) {
                            sucr.parent = root;
                        }
                        suc.left = rl;
                        rl.parent = suc;
                        suc.right = rr;
                        rr.parent = suc;
                        }
                    else {
    			        BSTree sucr = suc.right;
                         BSTree rp = root.parent;
                        
                        BSTree rl = root.left;
    			        
    			        suc.parent = rp;
    			    if (flag==0) {
        			    rp.right = suc;
                        } 
                    else {
                        rp.left = suc;
        			    
        		        }
    			    root.parent = suc;
                    suc.left = rl;
                     suc.right = root;
    			    
    			    rl.parent = suc;
                     root.right = sucr;
                    root.left = null;
    			    
    			    if (sucr != null) {
        			    sucr.parent = root;
        		    }
                    } 
                    
                    }
                //Check root has no child
                if(root.left==null && root.right==null){
                    BSTree p=root.parent;
                    if(p.left!=null && p.left==root){
                        root.parent=null;
                        p.left=null;
                        return true;
                    }
                    else{
                        root.parent=null;
                        p.right=null;
                        return true;
                    }
                 }
                if(root.right==null){
                    BSTree p=root.parent;
                    BSTree child=root.left;
                    if(p.right!=null && p.right==root){
                        root.parent=null;
                        root.left=null;
                        p.right=child;
                        child.parent=p;
                        return true;
                        }
                    else{
                        root.parent=null;
                        root.left=null;
                        p.left=child;
                        child.parent=p;
                        return true;
                    }
                    
                }
                if(root.left==null){
                     BSTree p=root.parent;
                     BSTree child=root.right;
                     if(p.left!=null && p.left==root){
                         root.parent=null;
                         root.right=null;
                         p.left=child;
                         child.parent=p;
                         return true;
                     }
                     else{
                         root.parent=null;
                         root.right=null;
                         p.right=child;
                         child.parent=p;
                         return true;
                         }
                 }
                 
                 return false;
    
    }
        
    public BSTree Find(int key, boolean exact)
    { 	    BSTree start=this.getFirst();
        if(start==null) return null;//Tree is empty
        
        if(exact){
            while(start.key!=key){
                
                start=start.getNext();
                if(start==null) return null;
                }
            return start;
            
            }
        else{
            while(start.key<key ){
                start=start.getNext();
                if(start==null) return null;
            }
            return start;
            
            
            }
        
        //return null;
    }

    public BSTree getFirst()
    {	   BSTree sentinal=this;
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null) return null;//Tree is empty
        }
        else{
            while(sentinal.parent!=null)
            sentinal=sentinal.parent;

        }    
       BSTree root=sentinal.right;
            while(root.left!=null){
                root=root.left;
                }
            return root;
            
            
       // return null;
    }

    public BSTree getNext()
    { 
        BSTree sentinal=this;
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null) return null;//Tree is empty
            else{//Return First smallest 
                BSTree root=sentinal.right;
                while(root.left!=null){
                    root=root.left;
                }
                return root;
            }
        }
        else{//Return successor
            BSTree temp=sentinal;
                if(temp.right!=null){//Successor is in the right sub tree
                    BSTree root=temp.right;
                    while(root.left!=null){
                        root=root.left;
                    }
                    return root;
                }
                else{//Successor is in the parent of temp node but check that there should exist a parent such that temp is left child 
                    //otherwise return null
                    BSTree parent=temp.parent;
                    while(parent.left!=temp && parent.parent!=null){
                        temp=parent;
                        parent=parent.parent;
                    }
                    if(parent.left==temp) return parent;
                    return null;
                }
            }
        //return null;

    }
    

    public boolean sanity()
    { BSTree root=this;
        if(root.parent==null && root.left==null && root.right==null) return true;
        
        while(root.parent!=null){
            root=root.parent;
        }
        root=root.right;//root node
        
        return check(root);//call check by root node
        //return false;
    }

}


 


