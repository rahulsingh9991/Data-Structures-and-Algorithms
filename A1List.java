// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {   A1List node=new A1List(address,size,key);
        A1List cur=this;//INsert node to the next of current node
       /* while(cur.prev!=null){
            cur=cur.prev;
        }*/
        if(cur.next==null) return null;

        A1List cur_next=this.next;
        node.next=cur_next;
        node.prev=cur;
        cur.next=node;
        cur_next.prev=node;
        
        //if(!cur.sanity()) return null;
        
        return node;
        //return null;
        

    }

    public boolean Delete(Dictionary d) 
    {   A1List cur=this.getFirst();
        if(cur==null) return false;
        else{
            while(cur.next!=null){
                if(cur.key==d.key){
                    if(cur.address==d.address && cur.size==d.size){
                        cur.next.prev=cur.prev;
                        cur.prev.next=cur.next;
                        cur.next=null;
                        cur.prev=null;
                        return true;
                    }
                }
                cur=cur.next;
            }
            return false;
        }
        

    }

    public A1List Find(int k, boolean exact)
    { A1List cur=this.getFirst();
        //Cur should be first node of the non-empty list
        if(cur==null) return null;//List is empty
        else{                       //list is not empty
            if(exact==true){
                while(cur.next!=null){
                    if(cur.key==k){
                        return cur;
                    }
                cur=cur.next;
                }

          return null;
      }  
            else{
                while(cur.next!=null){
                    if(cur.key>=k){
                        return cur;
                    }
                    cur=cur.next;
                    }

            return null;
            }
        
        }
        //return null;
    }

    public A1List getFirst()
    {   A1List cur=this;
        if(this.prev==null) {//Current is head sentinal
           if(this.next.next==null) return null;//list is empty
            else return this.next;
        }
        if(this.prev.prev==null && this.next==null) return null;//Current is tail sentinal and list is empty
        while(cur.prev.prev!=null){
                cur=cur.prev;
            }
        return cur;
        
        //return null;
    }
    
    public A1List getNext() 
    {   if(this.next==null) return null;//current is tail sentinal
        if(this.next.next!=null) return this.next;//current is not last element and also list is not empty
        return null;
        //return null;
    }

    public boolean sanity()
    {   A1List chk=this.getFirst();
        if(chk!=null){
            if(chk.prev==null || chk.prev.prev!=null) return false;
                }
        else{
            A1List temp=this;
            if(temp.next==null && temp.prev.prev!=null) return false;
            if(temp.prev==null && temp.next.next!=null) return false;
        }
        
        //Checking on all nodes
        A1List chk1=this;
        A1List chk2=this;
        while(chk1!=null && chk1.prev!=null){
            A1List chk1_prev=chk1.prev;
            if(chk1_prev.prev!=null && chk1_prev.prev.next!=chk1_prev) return false;
            if(chk1_prev.next!=chk1) return false;
            chk2=chk2.prev;
            chk1=chk1.prev.prev;
            
            if(chk1==chk2) return false;
        } 
        chk1=this;
        
        chk2=this;
        
        while(chk1!=null && chk1.next!=null){
            A1List chk1_next=chk1.next;
            if(chk1_next.next!=null && chk1_next.next.prev!=chk1_next) return false;
            if(chk1_next.prev!=chk1) return false;
            chk2=chk2.next;
            chk1=chk1.next.next;
            
            if(chk1==chk2) return false;
        }
        
       
        return true;
    }

}


