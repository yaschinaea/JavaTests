package com.packt.cookbook;

public class People
{
     private int ArmsCount;
     private int LegsCount;

     public People SetLegsCount(int count) throws Exception {
         if(count > 2) {
             throw new Exception();
         }
         LegsCount = count;
         return this;
     }

    public People SetArmsCount(int count) throws Exception {
        if(count > 2) {
            throw new Exception();
        }
        ArmsCount = count;
        return this;
    }

     public boolean IsInvalid()
     {
         if(LegsCount == 2 && ArmsCount ==2)
         {
             return false;
         }
         else
         {
             return true;
         }
     }
}
