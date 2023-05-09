// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) {
        if(blockSize<0) return -1;
        Dictionary node_block=freeBlk.Find(blockSize,false);
        if(node_block==null) return -1;
        else{
            //Split not required
            if(node_block.size==blockSize){
                
                allocBlk.Insert(node_block.address,node_block.size,node_block.address);//Chk Key??
                    
                    boolean chk=freeBlk.Delete(node_block);
                    //If chk is false throw an error exception
                    return node_block.address;
                }
            else{//Split Required
                int new_size=node_block.size-blockSize;
                
                allocBlk.Insert(node_block.address,blockSize,node_block.address);//CHK key??
                
                freeBlk.Insert(node_block.address+blockSize,new_size,new_size);
                
                boolean chk=freeBlk.Delete(node_block);
                //If chk is false throw an error exception
                return node_block.address;
                }
        }
        //return -1;
    } 
    
    public int Free(int startAddr) {
        if(startAddr<0) return -1;
        Dictionary node_block=allocBlk.Find(startAddr,true);
        if(node_block==null) return -1;
        else{
            
            freeBlk.Insert(node_block.address,node_block.size,node_block.size);
            boolean chk=allocBlk.Delete(node_block);
            //If chk is false throw an error exception
            return 0;
        }
        //return -1;
    }
}