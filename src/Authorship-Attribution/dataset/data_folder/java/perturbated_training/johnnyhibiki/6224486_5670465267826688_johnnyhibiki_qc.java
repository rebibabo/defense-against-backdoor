import java.util.*;
 
 public class QC {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int t = sc.nextInt();
        for(int test=1;test<=t;test++){
            long p = sc.nextLong();
            long q = sc.nextLong();
            String s = sc.next();
            char[] c = s.toCharArray();
 
            boolean is = false;
            boolean js = false;
            boolean ks = false;
            if(s.contains("i")) is = true;
            if(s.contains("j")) js = true;
            if(s.contains("k")) ks = true;
 
            String ans = "NO";
            if(p*q<=2 || !is && !js || !js && !ks || !ks && !is){
                
            }else if(p*q<=6){
                String tmp = "";
                for(int i=0;i<q;i++) tmp += s;
                char[] ca = tmp.toCharArray();
                
                String str = "";
                boolean f = true;
                char[] cc = new char[]{'i', 'j', 'k'};
                int ccid = 0;
                for(int i=0;i<p*q;i++){
                    if(i==p*q-1){
                        if(ca[i]!='1') str += ca[i];
                    }else if(ccid<3 && ca[i]==cc[ccid]){
                        str += cc[ccid];
                        ccid++;
                    }else{
                        char r = ca[i];
                        char a = ca[i+1];
                        if(a=='i'){
                            if(r=='i'){
                                r = '1';
                                f ^= true;
                            }else if(r=='j'){
                                r = 'k';
                                f ^= true;
                            }else if(r=='k'){
                                r = 'j';
                            }else if(r=='1'){
                                r = 'i';
                            }
                        }else if(a=='j'){
                            if(r=='i'){
                                r = 'k';
                            }else if(r=='j'){
                                r = '1';
                                f ^= true;
                            }else if(r=='k'){
                                r = 'i';
                                f ^= true;
                            }else if(r=='1'){
                                r = 'j';
                            }
                        }else if(a=='k'){
                            if(r=='i'){
                                r = 'j';
                                f ^= true;
                            }else if(r=='j'){
                                r = 'i';
                            }else if(r=='k'){
                                r = '1';
                                f ^= true;
                            }else if(r=='1'){
                                r = 'k';
                            }
                        }
                        ca[i+1] = r;
                    }
                }
                if(str.equals("ijk") && f) ans = "YES";
            }else{
                char r = '1';
                boolean f = true;
                for(char a : c){
                    if(a=='i'){
                        if(r=='i'){
                            r = '1';
                            f ^= true;
                        }else if(r=='j'){
                            r = 'k';
                            f ^= true;
                        }else if(r=='k'){
                            r = 'j';
                        }else if(r=='1'){
                            r = 'i';
                        }
                    }else if(a=='j'){
                        if(r=='i'){
                            r = 'k';
                        }else if(r=='j'){
                            r = '1';
                            f ^= true;
                        }else if(r=='k'){
                            r = 'i';
                            f ^= true;
                        }else if(r=='1'){
                            r = 'j';
                        }
                    }else if(a=='k'){
                        if(r=='i'){
                            r = 'j';
                            f ^= true;
                        }else if(r=='j'){
                            r = 'i';
                        }else if(r=='k'){
                            r = '1';
                            f ^= true;
                        }else if(r=='1'){
                            r = 'k';
                        }
                    }
                }
 
                if(r=='i' || r=='j' || r=='k'){
                    if((q+2)%4==0) ans = "YES"; 
                }else if(r=='1' && !f){
                    if(q%2==1) ans = "YES";
                }
            }
            System.out.println("case #" + test + ": " + ans);
        }
        sc.close();
    }
 
 }
