// Class: Height balanced AVL Tree
// Binary Search Tree
import java.util.Comparator;
public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private Comparator<AVLTree> comparator = new Comparator<AVLTree>(){
		@Override
		public int  compare(AVLTree T1, AVLTree T2){
		if(Integer.compare(T1.key, T2.key) == 0) {
            return Integer.compare(T1.address, T2.address);}
            return Integer.compare(T1.key, T2.key);
			
		}
	};
    private int height(AVLTree node){
        if(node==null) return -1;
        return node.height;
    }
    private int balance(AVLTree node){
        if(node==null) return 0;
        return height(node.left)-height(node.right);
    }
    private AVLTree leftrotate(AVLTree x){//check parent of node x should be updated
        AVLTree parent=x.parent;
         
        AVLTree y=x.right;
        AVLTree sub=y.left;
        y.left=x;
        x.parent=y;
        x.right=sub;
        if(sub!=null) sub.parent=x;
        
        x.height=Math.max(height(x.left),height(x.right))+1;
        y.height=Math.max(height(y.left),height(y.right))+1;
                //parent lin establish
                if(parent.left!=null && parent.left==x){
                    parent.left=y;
                    y.parent=parent;
                }
                else{
                    parent.right=y;
                    y.parent=parent;
                }
            
        return y;        

    }
    private AVLTree rightrotate(AVLTree x){
        AVLTree parent=x.parent;

        AVLTree y=x.left;
        AVLTree sub=y.right;
        y.right=x;
        x.parent=y;
        x.left=sub;
        if(sub!=null) sub.parent=x;
        
        x.height=Math.max(height(x.left),height(x.right))+1;
        y.height=Math.max(height(y.left),height(y.right))+1;
        //parent lin establish
        if(parent.left!=null && parent.left==x){
            parent.left=y;
            y.parent=parent;
        }
        else{
            parent.right=y;
            y.parent=parent;
        }
        return y;


    }
    
    private AVLTree ins_rec(AVLTree root,AVLTree node){
        if(root ==null) return node;
        int x;
        if(node.key<root.key){
            x=0;//left
        }
        else if(node.key>root.key){
            x=1;//right
        }
        else{
            if(node.address<root.address){
                x=0;
            }
            else{
                x=1;
            }
        }
        if(x==1){//insert in right of root
            int rooti,rootf,rootl;
            rooti=height(root.right);
            root.setR(ins_rec(root.right,node));
            rootf=height(root.right);
            if(rooti==rootf) return root;
            rootl=height(root.left);
            if(rootf-rootl==1){
                root.height=rootf+1;
                return root;
            }
            
             if(rootl>=rootf) {
                return root;
        } 
            AVLTree u,v;
            v=root.right;
            u=root;
            if(comparator.compare(node,v)<=0){
                AVLTree w,sub;
                
                w=v.left;
                sub=w.right;
                w.setR(v);
                u.setR(w);
                v.setL(sub);
                w.height=Math.max(height(w.right),height(w.left))+1;
                v.height=Math.max(height(v.right),height(v.left))+1;
                
                sub=w.left;
                u.setR(sub);
                w.setL(u);
                u.height=Math.max(height(u.right),height(u.left))+1;

                w.height=Math.max(height(w.right),height(w.left))+1;
                return w;
                }
            else{
                AVLTree w,sub;
                sub=v.left;
                w=v.right;
                u.setR(sub);
                v.setL(u);
                u.height=Math.max(height(u.right),height(u.left))+1;
                v.height=Math.max(height(v.right),height(v.left))+1;
                return v;
            }
        }
        else{
            int rooti,rootf,rootr;
            rooti=height(root.left);
            root.setL(ins_rec(root.left,node));
            rootf=height(root.left);
            if(rooti==rootf) return root;
            rootr=height(root.right);
            if(rootf-rootr==1){
                root.height=rootf+1;
                return root;

            }
            if(rootr>=rootf) return root;
            AVLTree u,v;
            v=root.left;
            u=root;
            if(comparator.compare(node,v)>0){
                AVLTree w,sub;
                w=v.right;
                sub=w.left;
                
                w.setL(v);
               u.setL(w);
                
                v.setR(sub);
                v.height=Math.max(height(v.right),height(v.left))+1;
                w.height=Math.max(height(w.right),height(w.left))+1;
                sub=w.right;
                w.setR(u);
                u.setL(sub);
                u.height=Math.max(height(u.right),height(u.left))+1;
                w.height=Math.max(height(w.right),height(w.left))+1;
                return w;
                
            }
            else{
                AVLTree w,sub;
                sub=v.right;
                 w=v.left;
                 u.setL(sub);
                 v.setR(u);
                u.height=Math.max(height(u.right),height(u.left))+1;
                v.height=Math.max(height(v.right),height(v.left))+1;
                return v;
                

            }

        }
        

    }
    private void setR(AVLTree node){
        AVLTree root=this;
        root.right=node;
        if(node==null) return;
        node.parent=root;
    }
    private void setL(AVLTree node){
        AVLTree root=this;
        root.left=node;
        if(node==null) return;
        node.parent=root;
    }

    public AVLTree Insert(int address, int size, int key) 
    {   AVLTree node=new AVLTree(address,size,key);
        AVLTree root=this;
        
        
        while(root.parent!=null)
            root=root.parent;
            
        root.setR(ins_rec(root.right,node));
        root.height=root.right.height+1;

        return node;
    }


    public boolean Delete(Dictionary e)
    {   if(e==null) return false;
        AVLTree sentinal=this;
        AVLTree node =new AVLTree(address,size,key);   
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null) return false;//Tree is empty
        }
        else{
            while(sentinal.parent!=null)
                sentinal=sentinal.parent;
        }
            
            
            
                AVLTree root=sentinal.right;
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
                    AVLTree p=root.parent;
                //check root has two child 
                if(root.left!=null && root.right!=null){
                    AVLTree suc = root.right;
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
                        AVLTree rp = root.parent;//p
                        AVLTree rr = root.right;
                        
                         AVLTree sucr = suc.right;
                        AVLTree rl = root.left;
                        
                        AVLTree sucp = suc.parent;
                        suc.parent = rp;
                        if (flag==0) {
                            rp.right = suc;//p
                            
                        } 
                        else {
                            rp.left = suc;//p
                        }
                        root.parent = sucp;//
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
    			        AVLTree sucr = suc.right;
                         AVLTree rp = root.parent;
                        
                        AVLTree rl = root.left;
    			        
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
        			    sucr.parent = root;}
                    } 
                    
                }
                //Check root has no child
                if(root.left==null && root.right==null){
                    p=root.parent;
                    if(p.left!=null && p.left==root){
                        root.parent=null;
                        p.left=null;
                        //return true;
                    }
                    else{
                        root.parent=null;
                        p.right=null;
                        //return true;
                    }
                 }
                if(root.parent!=null){
                if(root.right==null){
                    p=root.parent;
                    AVLTree child=root.left;
                    if(p.right!=null && p.right==root){
                        root.parent=null;
                        root.left=null;
                        p.right=child;
                        child.parent=p;
                        //return true;
                        }
                    else{
                        root.parent=null;
                        root.left=null;
                        p.left=child;
                        child.parent=p;
                        //return true;
                    }
                    
                }
                else{//if(root.left==null)
                    p=root.parent;
                     AVLTree child=root.right;
                     if(p.left!=null && p.left==root){
                         root.parent=null;
                         root.right=null;
                         p.left=child;
                         child.parent=p;
                         //return true;
                     }
                     else{
                         root.parent=null;
                         root.right=null;
                         p.right=child;
                         child.parent=p;
                         //return true;
                         }
                 }
                }
                
    

                        if(p.parent==null) return true;//single node present
                        p.height=Math.max(height(p.left),height(p.right))+1;
                        while(p.parent!=null){
                            //case of left left
                            int chk_bal=balance(p);
                            //Case of left left
                            if(chk_bal>1 && balance(p.left)>=0){
                                    p = rightrotate(p);
                                    }
                            //Case of right right
                            if(chk_bal<-1 && balance(p.right)<=0){
                                    p=leftrotate(p);
                                    }


                            //CASE of left right 
                            if(chk_bal >1 && balance(p.left)<0){
                                p.left=leftrotate(p.left);
                                p = rightrotate(p);
                                }
        
                            //CASE of right left 
                            if(chk_bal <-1 && balance(p.right)>0 ){
                                p.right=rightrotate(p.right);
                                p = leftrotate(p);
                                }
                            
                            p=p.parent;
                            if(p.parent==null) continue;
                            p.height=Math.max(height(p.left),height(p.right))+1;
                        }
                        return true;
                        //make a function check tree to check the height balance if true then return true
                        

        //return false;
    }
        
    public AVLTree Find(int k, boolean exact)
    {    AVLTree start=this.getFirst();
        //BSTree cur=this;

        if(start==null) return null;//Tree is empty
        
        if(exact){
            while(start.key!=k){
                
                start=start.getNext();
                if(start==null) return null;
                }
            return start;
            
            }
        else{
            while(start.key<k ){
                start=start.getNext();
                if(start==null) return null;
            }
            return start;
            
            
            }
    
    }

    public AVLTree getFirst()
    {    AVLTree sentinal=this;
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null && sentinal.left==null) return null;//Tree is empty
        }
        else{
            while(sentinal.parent!=null)
            sentinal=sentinal.parent;

        }    
       AVLTree root=sentinal.right;
            while(root.left!=null){
                root=root.left;
                }
            return root;
            
    }

    public AVLTree getNext()
    {
        AVLTree sentinal=this;
        if(sentinal.parent==null){//chk current node is sentinal
            if(sentinal.right==null && sentinal.left==null) return null;//Tree is empty
            else{//Return First smallest 
                AVLTree root=sentinal.right;
                while(root.left!=null){
                    root=root.left;
                }
                return root;
            }
        }
        else{//Return successor
            AVLTree temp=sentinal;
                if(temp.right!=null){//Successor is in the right sub tree
                    AVLTree root=temp.right;
                    while(root.left!=null){
                        root=root.left;
                    }
                    return root;
                }
                else{//Successor is in the parent of temp node but check that there should exist a parent such that temp is left child 
                    //otherwise return null
                    AVLTree parent=temp.parent;
                    while(parent.left!=temp && parent.parent!=null){
                        temp=parent;
                        parent=parent.parent;
                    }
                    if(parent.left==temp) return parent;
                    return null;
                }
            }
       // return null;
    }
  
    private boolean check(AVLTree root){
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
        int chk1=Math.abs(height(root.left)-height(root.right));
        
        boolean chk2=(height(root)==(1+Math.max(height(root.left),height(root.right)))) ? true : false;
        return (chk1<=1 && chk2);
    
        //return true;
        }
    public boolean sanity()
    {   AVLTree root=this;
        if(root.parent==null && root.left==null && root.right==null) return true;
        
        while(root.parent!=null){
            root=root.parent;
        }
        root=root.right;//root node
        
        return check(root);//call check by root node
        //return false;
    }




}


