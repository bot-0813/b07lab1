
public class Polynomial{
    
    public double[] coeff; 
    public int[] expo;
      
Polynomial(double arr1[], int arr2[]){
     ArrayList<Double> coefflist = new ArrayList<>();
     ArrayList<Integer> expolist = new ArrayList<>();

     for (int i = 0; i < arr1.length; i++){
          if (arr1[i] != 0){
               coefflist.add(arr1[i]);  
               expolist.add(arr2[i]);   
           }
      }
      coeff = new double[coefflist.size()];
      expo = new int[expolist.size()];
      for (int i = 0; i < coefflist.size(); i++) {
            coeff[i] = coefflist.get(i);
            expo[i] = expolist.get(i);
        }
    } 

  
Polynomial add(Polynomial p1){
       int new_length = this.expo.length + p1.expo.length;
       double result[] = new double[new_length];
       int result_2 [] = new int [new_length];
       int i = 0;
       int j = 0;
      for(int x:p1.expo){
                  
         if(is_there(this.expo,x) == true){
             int k = index(this.expo, x);
             if( k != -1){
               result[i] = p1.coeff[j] + this.coeff[k];
               result_2[i] = x;
               i++;
              }
         }
         else{
              result[i] = p1.coeff[j];
              result_2[i] = x;
              i++;
             }
         j++;
          }
      int k = 0;
      for(int y: this.expo){
            if(is_there(result_2,y,i) == false){
               result[i] = this.coeff[k];
               result_2[i] = y;
               i++;
           }
           k++;
      }

      return new Polynomial(result,result_2);




double evaluate(double x){
    double sum = 0;
    for(int i = 0; i < this.coeff.length;i++){
    sum += (this.coeff[i] * Math.pow(x,this.expo[i]); 
    }
  return sum;
}

boolean hasRoot(double x){
   return evaluate(x)==0;
}



boolean is_there(int arr[], int num,int range){
for(int i = 0; i<range;i++){
  if (arr[i]==num)
    return true;
}
return false;
}

int index(int arr[], int x){
for(int i = 0; i < arr.length;i++){
  if(arr[i] == x)
	return i;
}
return -1;
}	



Polynomial multiply(Polynomial p1){
	int new_length = this.coeff.length + p1.coeff.length;
	double result[] = new double[new_length];
	int result_2 [] = new int [new_length];
        int k = 0;
	for(int i = 0; i < p1.coeff.length; i++){
  	   for(int j = 0; j < this.coeff.length; j++){
              result[k] = p1.coeff[i]*this.coeff[j];
              result_2[k] = p1.expo[i] + this.expo[j];
              k++;
           }
        }
     double result_3[] = new double[new_length];     
     int result_4[] = new int [new_length];
     int m = 0;
    for (int i = 0; i < result_2.length; i++) {
    boolean found = false;        // To track if duplicates are found
    
    for (int j = i + 1; j < result_2.length; j++) {
        if (result_2[i] == result_2[j]) {
            result_3[m] = result[i] + result[j];
            result_4[m] = result_2[i];
            found = true;        // found the duplicate
            result_2[j] = -1;
        }
    }

    if (!found && result_2[i] != -1) {
        result_3[m] = result[i];
        result_4[m] = result_2[i];
    }
    
    m++;               
   }

return new Polynomial(result_3, result_4);
}



Polynomial(File input){
  Scanner s = new Scanner(file);
 while(s.hasNextLine()){
  String line = s.nextline();
  String[] data = line.split("(?=[+-])");
     int length = data.length;
     double[] coeff = new double[length];
     int[] expo = new int[length];
     int i = 0;
     for( String x:data){
        
         if (!x.contains("x")) {
             coeff[i] = Double.parseDouble(x);
            expo[i] = 0;
        }
         else{
             int index = x.indexOf("x");
         String sub_string = x.substring(0,index);
         String sub_str_2 = x.substring(index+1);
             coeff[i] = Double.parseDouble(sub_string);
             expo[i] = Integer.parseInt(sub_str_2);
         }
        i++;
    }
}

void saveToFile(String name){
   String s = "";
    for(int i = 0; i < this.coeff.length;i++){
        if(this.expo[i] == 0 && i == 0)
          s += this.coeff[i];
        else if(this.expo[i] == 0 && i != 0)
          s += "+"+this.coeff[i];
        else if(this.coeff[i] != 0 && i == 0)
          s += this.coeff[i]+"x"+this.expo[i];
        else if(this.coeff[i] > 0 && i != 0)
         s += "+"+this.coeff[i]+"x"+this.expo[i];
        else
         s += this.coeff[i]+"x"+this.expo[i];
    }
  FileWriter writer = new FileWriter(name);
    writer.write(s);
   writer.close();
}



















