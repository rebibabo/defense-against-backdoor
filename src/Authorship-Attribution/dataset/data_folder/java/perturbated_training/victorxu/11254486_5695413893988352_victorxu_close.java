import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.FileReader;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.io.PrintWriter;
 
 
 public class Close {
    public static void main(String[] args) throws IOException{
        BufferedReader buf = new BufferedReader(new FileReader("close.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("close.out")));
        int q = Integer.parseInt(buf.readLine());
        for(int i = 1; i <= q; i++){
            String[] s = buf.readLine().split(" ");
            String first = s[0];
            String second = s[1];
            int pos = -1;
            String prefix = "";
            int check = -1;
            boolean nonzero = false;
            boolean bad = false;
            for(int j = 0; j < first.length(); j++){
                
                if(first.charAt(j) == '?' || second.charAt(j) == '?'){
                    if(first.charAt(j) == '?' && second.charAt(j) == '?'){
                        prefix = prefix + "0";
                        check = 1;
                    } else if(first.charAt(j) != '?'){
                        prefix = prefix + first.charAt(j);
                        if(first.charAt(j) != '0') nonzero = true;
                        check = 2;
                    } else if(second.charAt(j) != '?'){
                        prefix = prefix + second.charAt(j);
                        if(second.charAt(j) != '0') nonzero = true;
                        check = 3;
                    } else {
                        System.out.println("ERROR");
                    }
                } else if(first.charAt(j) == second.charAt(j)) {
                    prefix = prefix + first.charAt(j);
                    if(first.charAt(j) != 0) nonzero = true;
                    check = 4;
                } else {
                    pos = j;
                    bad = true;
                    break;
                }
            }
            String fs = null;
            String ss = null;
            if(prefix.length() == 0){
                if(first.charAt(pos) > second.charAt(pos)){
                    fs = min(first.substring(pos+1));
                    ss = max(second.substring(pos+1));
                } else {
                    fs = max(first.substring(pos+1));
                    ss = min(second.substring(pos+1));
                }
                out.println("Case #" + i + ": " + first.charAt(pos) + fs + " " + second.charAt(pos) + ss);
            } else if(bad){
                int fp = Integer.parseInt(prefix);
                int sp = Integer.parseInt(prefix);
                int diff = Math.abs(first.charAt(pos) - second.charAt(pos));
                if(diff < 5 || check == 4){
                    if(first.charAt(pos) > second.charAt(pos)){
                        fs = min(first.substring(pos+1));
                        ss = max(second.substring(pos+1));
                    } else {
                        fs = max(first.substring(pos+1));
                        ss = min(second.substring(pos+1));
                    }
                } else if(diff > 5) {
                    if(first.charAt(pos) > second.charAt(pos)){
                        if(nonzero && check(first.substring(0, pos), Integer.toString(fp-1))){
                            fp--;
                            fs = max(first.substring(pos+1));
                            ss = min(second.substring(pos+1));
                        } else if (check(second.substring(0, pos), Integer.toString(sp+1))){
                            sp++;
                            fs = max(first.substring(pos+1));
                            ss = min(second.substring(pos+1));
                        } else {
                            fs = min(first.substring(pos+1));
                            ss = max(second.substring(pos+1));
                        }
                    } else {
                        
                        if(nonzero && check(second.substring(0, pos), Integer.toString(sp-1))){
                            sp--;
                            fs = min(first.substring(pos+1));
                            ss = max(second.substring(pos+1));
                        } else if (check(first.substring(0, pos), Integer.toString(fp+1))){
                            fp++;
                            fs = min(first.substring(pos+1));
                            ss = max(second.substring(pos+1));
                        } else {
                            fs = max(first.substring(pos+1));
                            ss = min(second.substring(pos+1));
                        }
                    }
                } else {
                    
                    int d1 = Integer.MAX_VALUE;
                    if(first.charAt(pos) > second.charAt(pos)){
                        boolean b = false;
                        if(nonzero && check(first.substring(0, pos), Integer.toString(fp-1))){
                            fp--;
                            String fs1 = max(first.substring(pos+1));
                            String ss1 = min(second.substring(pos+1));
                            String fstr = Integer.toString(fp);
                            for(int j = fstr.length(); j < pos; j++){
                                fstr = "0" + fstr;
                            }
                            String sstr = Integer.toString(sp);
                            for(int j = sstr.length(); j < pos; j++){
                                sstr = "0" + sstr;
                            }
                            d1 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs1) - Integer.parseInt(sstr + second.charAt(pos) + ss1));
                            fp++;
                        } else if (check(second.substring(0, pos), Integer.toString(sp+1))){
                            sp++;
                            String fs1 = max(first.substring(pos+1));
                            String ss1 = min(second.substring(pos+1));
                            String fstr = Integer.toString(fp);
                            for(int j = fstr.length(); j < pos; j++){
                                fstr = "0" + fstr;
                            }
                            String sstr = Integer.toString(sp);
                            for(int j = sstr.length(); j < pos; j++){
                                sstr = "0" + sstr;
                            }
                            d1 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs1) - Integer.parseInt(sstr + second.charAt(pos) + ss1));
                            b = true;
                            sp--;
                        }
                        String fs2 = min(first.substring(pos+1));
                        String ss2 = max(second.substring(pos+1));
                        String fstr = Integer.toString(fp);
                        for(int j = fstr.length(); j < pos; j++){
                            fstr = "0" + fstr;
                        }
                        String sstr = Integer.toString(sp);
                        for(int j = sstr.length(); j < pos; j++){
                            sstr = "0" + sstr;
                        }
                        int d2 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs2) - Integer.parseInt(sstr + second.charAt(pos) + ss2));
                        if(d1 < d2 || (d1 == d2 && !(b))) {
                            fs = max(first.substring(pos+1));
                            ss = min(second.substring(pos+1));
                            if(b) sp++; else fp--;
                        } else {
                            fs = fs2;
                            ss = ss2;
                        }
                    } else {
                        boolean b = false;
                        if(nonzero && check(second.substring(0, pos), Integer.toString(sp-1))){
                            sp--;
                            String fs1 = min(first.substring(pos+1));
                            String ss1 = max(second.substring(pos+1));
                            String fstr = Integer.toString(fp);
                            for(int j = fstr.length(); j < pos; j++){
                                fstr = "0" + fstr;
                            }
                            String sstr = Integer.toString(sp);
                            for(int j = sstr.length(); j < pos; j++){
                                sstr = "0" + sstr;
                            }
                            d1 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs1) - Integer.parseInt(sstr + second.charAt(pos) + ss1));
                            sp++;
                        } else if (check(first.substring(0, pos), Integer.toString(fp+1))){
                            fp++;
                            String fs1 = min(first.substring(pos+1));
                            String ss1 = max(second.substring(pos+1));
                            String fstr = Integer.toString(fp);
                            for(int j = fstr.length(); j < pos; j++){
                                fstr = "0" + fstr;
                            }
                            String sstr = Integer.toString(sp);
                            for(int j = sstr.length(); j < pos; j++){
                                sstr = "0" + sstr;
                            }
                            d1 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs1) - Integer.parseInt(sstr + second.charAt(pos) + ss1));
                            b = true;
                            fp--;
                        }
                        String fs2 = max(first.substring(pos+1));
                        String ss2 = min(second.substring(pos+1));
                        String fstr = Integer.toString(fp);
                        for(int j = fstr.length(); j < pos; j++){
                            fstr = "0" + fstr;
                        }
                        String sstr = Integer.toString(sp);
                        for(int j = sstr.length(); j < pos; j++){
                            sstr = "0" + sstr;
                        }
                        int d2 = Math.abs(Integer.parseInt(fstr + first.charAt(pos) + fs2) - Integer.parseInt(sstr + second.charAt(pos) + ss2));
                        if(d1 < d2 || (d1 == d2 && !b)) {
                            fs = min(first.substring(pos+1));
                            ss = max(second.substring(pos+1));
                            if (b) fp++; else sp--;
                        } else {
                            fs = fs2;
                            ss = ss2;
                        }
                        
                    }
                    
                    System.out.println("Case #" + i + ": " + Integer.toString(fp) + first.charAt(pos) + fs + " " + Integer.toString(sp) + second.charAt(pos) + ss);
                }
                String fstr = Integer.toString(fp);
                for(int j = fstr.length(); j < pos; j++){
                    fstr = "0" + fstr;
                }
                String sstr = Integer.toString(sp);
                for(int j = sstr.length(); j < pos; j++){
                    sstr = "0" + sstr;
                }
                out.println("Case #" + i + ": " + fstr + first.charAt(pos) + fs + " " + sstr + second.charAt(pos) + ss);
            } else {
                out.println("Case #" + i + ": " + prefix + " " + prefix);
            }
        }
        
        buf.close();
        out.close();
    }
    public static String min(String a){
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == '?'){
                res = res + "0";
            } else {
                res = res + a.charAt(i);
            }
        }
        return res;
    }
    public static boolean check(String pat, String b){
        if(b.length() > pat.length()) return false;
        for(int i = b.length(); i < pat.length(); i++){
            b = "0" + b;
        }
        
        for(int i = 0; i < pat.length(); i++){
            if(pat.charAt(i) == '?'){
                continue;
            } else {
                if(pat.charAt(i) != b.charAt(i)) return false;
            }
        }
        return true;
    }
    public static String max(String a){
        String res = "";
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == '?'){
                res = res + "9";
            } else {
                res = res + a.charAt(i);
            }
        }
        return res;
    }
 }
