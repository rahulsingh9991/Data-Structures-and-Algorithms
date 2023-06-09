// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {
        Dictionary addorder;
        if(this.type==3){
            addorder=new BSTree();

        }
        else if(this.type==2){
            addorder=new BSTree();
        }
        
        else return;
        Dictionary addorder1=freeBlk.getFirst();
        while(addorder1!=null){
            addorder.Insert(addorder1.address,addorder1.size,addorder1.address);//Inserting with key as memory address
            addorder1=addorder1.getNext();
            
        }
        if(this.type==3){
            freeBlk=new BSTree();

        }
        else if(this.type==2){
                freeBlk=new BSTree();
            }
        
        else return;
        Dictionary ele=addorder.getFirst();
        if(ele==null) return;
        Dictionary temp=ele.getNext();
        while(temp!=null){
            if(ele.address+ele.size==temp.address){
                ele.size=ele.size+temp.size;
                addorder.Delete(temp);
                temp=ele.getNext();
                continue;
            }
                ele=ele.getNext();
                temp=temp.getNext();
        }
        ele=addorder.getFirst();
        while(ele!=null){
            freeBlk.Insert(ele.address,ele.size,ele.size);
            ele=ele.getNext();
        }

    }
    




}