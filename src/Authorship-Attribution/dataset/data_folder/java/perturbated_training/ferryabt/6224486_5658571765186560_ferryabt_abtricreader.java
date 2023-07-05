package helpers;
 
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.ArrayList;
 
 public class AbtRicReader {
    
    public enum Type{
        INTEGER, STRING;
    }
 
    FileReader reader;
    public String[] buffer;
    boolean done = false;
 
    public AbtRicReader() {
 
    }
 
    public AbtRicReader(String file) {
        try {
            reader = new FileReader(file);
            int tmp = reader.read();
            String current = "";
            ArrayList<String> bufferlist = new ArrayList<String>();
            while (true) {
                if(tmp == 10) {
                    bufferlist.add(current);
                    break;
                } else if(tmp == 32) {
                    bufferlist.add(current);
                    current = "";
                    tmp = reader.read();
                } else {
                    current += (char) tmp;
                    tmp = reader.read();
                }
            }
            String[] tmpString = new String[bufferlist.size()];
            buffer = bufferlist.toArray(tmpString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public String[] getLine() {
        String[] output = buffer;
        try {
            int tmp = reader.read();
            if (tmp == -1) {
                done = true;
                reader.close();
            } else {
                String current = "";
                ArrayList<String> bufferlist = new ArrayList<String>();
                while (true) {
                    if(tmp == 10) {
                        bufferlist.add(current);
                        break;
                    } else if(tmp == 32) {
                        bufferlist.add(current);
                        current = "";
                        tmp = reader.read();
                    } else {
                        current += (char) tmp;
                        tmp = reader.read();
                    }
                }
                String[] tmpString = new String[bufferlist.size()];
                buffer = bufferlist.toArray(tmpString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    public boolean notDone() {
        return !done;
    }
    
    public int[] toInteger(String[] input) {
        int[] output = new int[input.length];
        for(int i=0;i<input.length;i++) {
            output[i]=Integer.parseInt(input[i]);
        }
        return output;
    }
    
    public long[] toLong(String[] input) {
        long[] output = new long[input.length];
        for(int i=0;i<input.length;i++) {
            output[i]=Integer.parseInt(input[i]);
        }
        return output;
    }
 
    
    public int[] getNumberOfCases(String path, int count) {
        int output[] = new int[count];
        try {
            FileReader reader = new FileReader(path);
            int tmp = reader.read();
            String numberAsString = "";
            int i = 0;
            while (true) {
                if (tmp == 10 || tmp == 13) {
                    output[i] = Integer.parseInt(numberAsString);
                    break;
                } else if (tmp == 32) {
                    output[i] = Integer.parseInt(numberAsString);
                    i++;
                    numberAsString = "";
                } else {
                    numberAsString += (char) tmp;
                }
                tmp = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    
    public String readFile(String path) {
        String output = "";
        try {
            FileReader reader = new FileReader(path);
            int tmp = reader.read();
            while (tmp != -1) {
                output += (char) tmp;
                tmp = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    
    public ArrayList<String[]> readWordFile(String path) {
        ArrayList<String[]> output = new ArrayList<String[]>();
        try {
            FileReader reader = new FileReader(path);
            int buffer = reader.read();
            while (buffer != 10) {
                buffer = reader.read();
            }
            buffer = reader.read();
            ArrayList<String> currentList = new ArrayList<String>();
            String current = "";
            while (true) {
                if (buffer == 10) {
                    currentList.add(current);
                    current = "";
                    String[] help = new String[currentList.size()];
                    output.add(currentList.toArray(help));
                    currentList.clear();
                } else if (buffer == 32) {
                    currentList.add(current);
                    current = "";
                } else if (buffer == -1) {
                    currentList.add(current);
                    String[] help = new String[currentList.size()];
                    output.add(currentList.toArray(help));
                    break;
                } else {
                    current += (char) buffer;
                }
                buffer = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    
    public ArrayList<Integer[]> readIntFile(String path) {
        ArrayList<Integer[]> output = new ArrayList<Integer[]>();
        try {
            FileReader reader = new FileReader(path);
            int buffer = reader.read();
            while (buffer != 10) {
                buffer = reader.read();
            }
            buffer = reader.read();
            ArrayList<Integer> currentList = new ArrayList<Integer>();
            String current = "";
            while (true) {
                if (buffer == 10) {
                    currentList.add(Integer.parseInt(current));
                    current = "";
                    Integer[] help = new Integer[currentList.size()];
                    output.add(currentList.toArray(help));
                    currentList.clear();
                } else if (buffer == 32) {
                    currentList.add(Integer.parseInt(current));
                    current = "";
                } else if (buffer == -1) {
                    
                    
                    
                    break;
                } else {
                    current += (char) buffer;
                }
                buffer = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    
    public ArrayList<Long[]> readLongFile(String path) {
        ArrayList<Long[]> output = new ArrayList<Long[]>();
        try {
            FileReader reader = new FileReader(path);
            int buffer = reader.read();
            while (buffer != 10) {
                buffer = reader.read();
            }
            buffer = reader.read();
            ArrayList<Long> currentList = new ArrayList<Long>();
            String current = "";
            while (true) {
                if (buffer == 10) {
                    currentList.add(Long.parseLong(current));
                    current = "";
                    Long[] help = new Long[currentList.size()];
                    output.add(currentList.toArray(help));
                    currentList.clear();
                } else if (buffer == 32) {
                    currentList.add(Long.parseLong(current));
                    current = "";
                } else if (buffer == -1) {
                    currentList.add(Long.parseLong(current));
                    Long[] help = new Long[currentList.size()];
                    output.add(currentList.toArray(help));
                    break;
                } else {
                    current += (char) buffer;
                }
                buffer = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 
    
    public ArrayList<Float[]> readFloatFile(String path) {
        ArrayList<Float[]> output = new ArrayList<Float[]>();
        try {
            FileReader reader = new FileReader(path);
            int buffer = reader.read();
            while (buffer != 10) {
                buffer = reader.read();
            }
            buffer = reader.read();
            ArrayList<Float> currentList = new ArrayList<Float>();
            String current = "";
            while (true) {
                if (buffer == 10) {
                    currentList.add(Float.parseFloat(current));
                    current = "";
                    Float[] help = new Float[currentList.size()];
                    output.add(currentList.toArray(help));
                    currentList.clear();
                } else if (buffer == 32) {
                    currentList.add(Float.parseFloat(current));
                    current = "";
                } else if (buffer == -1) {
                    currentList.add(Float.parseFloat(current));
                    Float[] help = new Float[currentList.size()];
                    output.add(currentList.toArray(help));
                    break;
                } else {
                    current += (char) buffer;
                }
                buffer = reader.read();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
 }
