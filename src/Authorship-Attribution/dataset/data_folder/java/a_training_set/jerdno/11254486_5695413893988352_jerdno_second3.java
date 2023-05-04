package com.company;
 
 import java.util.LinkedList;
 import java.util.List;
 
 public class Second3 {
    public static String computeRow(int index) {
        String resultC = "";
        String resultJ = "";
        String c = Main.nextToken();
        String j = Main.nextToken();
        Integer dvojica = null;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) != '?' && j.charAt(i) != '?' && c.charAt(i) != j.charAt(i)) {
                dvojica = i;
                break;
            }
        }
        if (dvojica == null) {
            for (int i = 0; i < c.length(); i++) {
                if (c.charAt(i) == '?' && j.charAt(i) == '?') {
                    resultC = resultC + 0;
                    resultJ = resultJ + 0;
                } else {
                    if (c.charAt(i) == '?') {
                        resultC = resultC + j.charAt(i);
                        resultJ = resultJ + j.charAt(i);
                    } else {
                        resultC = resultC + c.charAt(i);
                        resultJ = resultJ + c.charAt(i);
                    }
                }
            }
        } else {
            if (speci(c, j, dvojica)) {
                boolean done = false;
                for (int i = dvojica - 1; i >= 0; i--) {
                    if (done) {
                        if (c.charAt(i) == '?' && j.charAt(i) == '?') {
                            resultC = 0 + resultC;
                            resultJ = 0 + resultJ;
                        } else {
                            if (c.charAt(i) == '?') {
                                resultC = j.charAt(i) + resultC;
                                resultJ = j.charAt(i) + resultJ;
                            } else {
                                resultC = c.charAt(i) + resultC;
                                resultJ = c.charAt(i) + resultJ;
                            }
                        }
                    } else {
                        if (c.charAt(dvojica) > j.charAt(dvojica)) {
                            if (c.charAt(i) == '?' && j.charAt(i) == '?') {
                                resultC = 0 + resultC;
                                resultJ = 1 + resultJ;
                                done = true;
                            } else {
                                if (c.charAt(i) == '?') {
                                    resultC = fix(Character.getNumericValue(j.charAt(i)) - 1) + resultC;
                                    resultJ = j.charAt(i) + resultJ;
                                    if (j.charAt(i) != '0') {
                                        done = true;
                                    }
                                } else if (j.charAt(i) == '?') {
                                    resultC = c.charAt(i) + resultC;
                                    resultJ = fix(Character.getNumericValue(c.charAt(i)) + 1) + resultJ;
                                    if (c.charAt(i) != '9') {
                                        done = true;
                                    }
                                } else {
                                    resultC = c.charAt(i) + resultC;
                                    resultJ = c.charAt(i) + resultJ;
                                }
                            }
                        } else {
                            if (c.charAt(i) == '?' && j.charAt(i) == '?') {
                                resultC = 1 + resultC;
                                resultJ = 0 + resultJ;
                                done = true;
                            } else {
                                if (c.charAt(i) == '?') {
                                    resultC = fix(Character.getNumericValue(j.charAt(i)) + 1) + resultC;
                                    resultJ = j.charAt(i) + resultJ;
                                    if (j.charAt(i) != '9') {
                                        done = true;
                                    }
                                } else if (j.charAt(i) == '?') {
                                    resultC = c.charAt(i) + resultC;
                                    resultJ = fix(Character.getNumericValue(c.charAt(i)) - 1) + resultJ;
                                    if (c.charAt(i) != '0') {
                                        done = true;
                                    }
                                } else {
                                    resultC = c.charAt(i) + resultC;
                                    resultJ = c.charAt(i) + resultJ;
                                }
                            }
                        }
                    }
                }
                for (int i = dvojica; i < c.length(); i++) {
                    if (c.charAt(dvojica) > j.charAt(dvojica)) {
                        if (c.charAt(i) == '?') {
                            resultC = resultC + 9;
                        } else {
                            resultC = resultC + c.charAt(i);
                        }
                        if (j.charAt(i) == '?') {
                            resultJ = resultJ + 0;
                        } else {
                            resultJ = resultJ + j.charAt(i);
                        }
                    } else {
                        if (c.charAt(i) == '?') {
                            resultC = resultC + 0;
                        } else {
                            resultC = resultC + c.charAt(i);
                        }
                        if (j.charAt(i) == '?') {
                            resultJ = resultJ + 9;
                        } else {
                            resultJ = resultJ + j.charAt(i);
                        }
                    }
                }
            } else {
                for (int i = 0; i < dvojica; i++) {
                    if (c.charAt(i) == '?' && j.charAt(i) == '?') {
                        resultC = resultC + 0;
                        resultJ = resultJ + 0;
                    } else {
                        if (c.charAt(i) == '?') {
                            resultC = resultC + j.charAt(i);
                            resultJ = resultJ + j.charAt(i);
                        } else {
                            resultC = resultC + c.charAt(i);
                            resultJ = resultJ + c.charAt(i);
                        }
                    }
                }
                for (int i = dvojica; i < c.length(); i++) {
                    if (c.charAt(dvojica) > j.charAt(dvojica)) {
                        if (c.charAt(i) == '?') {
                            resultC = resultC + 0;
                        } else {
                            resultC = resultC + c.charAt(i);
                        }
                        if (j.charAt(i) == '?') {
                            resultJ = resultJ + 9;
                        } else {
                            resultJ = resultJ + j.charAt(i);
                        }
                    } else {
                        if (c.charAt(i) == '?') {
                            resultC = resultC + 9;
                        } else {
                            resultC = resultC + c.charAt(i);
                        }
                        if (j.charAt(i) == '?') {
                            resultJ = resultJ + 0;
                        } else {
                            resultJ = resultJ + j.charAt(i);
                        }
                    }
                }
            }
        }
        return "Case #"+ index +": " + resultC + " " + resultJ;
    }
 
    private static boolean speci(String c, String j, Integer dvojica) {
        if (c.charAt(dvojica) > j.charAt(dvojica)) {
            if (Character.getNumericValue(c.charAt(dvojica)) - Character.getNumericValue(j.charAt(dvojica)) > Math.abs((10 + Character.getNumericValue(j.charAt(dvojica))) - Character.getNumericValue(c.charAt(dvojica)))) {
                for (int i = dvojica - 1; i >= 0; i--) {
                    if (c.charAt(i) == '?' && j.charAt(i) == '?')
                        return true;
                    if (c.charAt(i) == '?' && j.charAt(i) != '0')
                        return true;
                    if (j.charAt(i) == '?' && c.charAt(i) != '9')
                        return true;
                    if (c.charAt(i) != '?' && j.charAt(i) != '?')
                        return false;
                }
            }
            if (Character.getNumericValue(c.charAt(dvojica)) - Character.getNumericValue(j.charAt(dvojica)) == Math.abs((10 + Character.getNumericValue(j.charAt(dvojica))) - Character.getNumericValue(c.charAt(dvojica)))) {
                for (int i = dvojica - 1; i >= 0; i--) {
                    if (c.charAt(i) == '?' && j.charAt(i) == '?')
                        return false;
                    if (c.charAt(i) == '?' && j.charAt(i) != '0')
                        return true;
                    if (c.charAt(i) != '?' && j.charAt(i) != '?')
                        return false;
                }
            }
        } else {
            if (Character.getNumericValue(j.charAt(dvojica)) - Character.getNumericValue(c.charAt(dvojica)) > Math.abs((10 + Character.getNumericValue(c.charAt(dvojica))) - Character.getNumericValue(j.charAt(dvojica)))) {
                for (int i = dvojica - 1; i >= 0; i--) {
                    if (c.charAt(i) == '?' && j.charAt(i) == '?')
                        return true;
                    if (c.charAt(i) == '?' && j.charAt(i) != '9')
                        return true;
                    if (j.charAt(i) == '?' && c.charAt(i) != '0')
                        return true;
                    if (c.charAt(i) != '?' && j.charAt(i) != '?')
                        return false;
                }
            }
            if (Character.getNumericValue(j.charAt(dvojica)) - Character.getNumericValue(c.charAt(dvojica)) == Math.abs((10 + Character.getNumericValue(c.charAt(dvojica))) - Character.getNumericValue(j.charAt(dvojica)))) {
                for (int i = dvojica - 1; i >= 0; i--) {
                    if (c.charAt(i) == '?' && j.charAt(i) == '?')
                        return false;
                    if (j.charAt(i) == '?' && c.charAt(i) != '0')
                        return true;
                    if (c.charAt(i) == '?' && j.charAt(i) == '?')
                        return false;
                }
            }
        }
        return false;
    }
 
    private static Integer fix(Integer i) {
        if (i == -1)
            return 9;
        if (i == 10)
            return 0;
        return i;
    }
 }
