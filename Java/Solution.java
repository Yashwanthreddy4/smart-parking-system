import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        perm(nums,0,list);
        return list;
        
    }
    public void perm(int []nums,int fix,List<List<Integer>>list)
    {
        if(fix==nums.length-1)
        {
            List<Integer> temp=new ArrayList<Integer>();
            for (int i:nums)
                temp.add(i);
            list.add(temp);
            return;
        }
        for(int i=fix;i<nums.length;i++)
        {
            int t=nums[i];
            nums[i]=nums[fix];
            nums[fix]=t;
            perm(nums,fix+1,list);
             t=nums[i];
            nums[i]=nums[fix];
            nums[fix]=t;

        }
    }
}