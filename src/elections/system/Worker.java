package elections.system;

import elections.system.votingStations.QuarantineVotingStation;
import elections.system.votingStations.RegularVotingStation;
import elections.system.votingStations.SpecialVotingStation;
import elections.system.votingStations.VotingStation;

import java.io.BufferedReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Worker {


    public String readNewLine(BufferedReader reader){
        String result = "nope";
        try {
            result = reader.readLine();
        }
        catch (Exception e) {
            readNewLine(reader);
        }
        return result;
    }
    public int readInt(BufferedReader reader){
        int result = 0;
        while(true) {
            try {
                result = Integer.parseInt(reader.readLine());
                if (result >= 0) break;
            } catch (Exception e) {
                System.out.println("Please enter only positive numbers.");
            }
        }
        return result;
    }
    private double readDouble(BufferedReader reader){
        double result = 0;
        while (true) {
            try {
                result = Double.parseDouble(readNewLine(reader));
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer or fractional number.");
            }
        }
        return result;
    }
    public String readString(BufferedReader reader){
        String result = "";
        while(true) {
            try {
                result = reader.readLine();
                if (result.matches("[a-zA-Z]*")) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter an Text, without numbers");
            }
        }
        return result;
    }
    public String readName(BufferedReader reader){
        String result = "";
        while(true) {
            try {
                result = readString(reader);
                if (result.matches("^[A-Z][a-z]*")) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("The first letter must be in uppercase, the rest are in lowercase.");
            }
        }

        return result;
    }

    public String readINN(BufferedReader reader) {
        String result = "";
        while(true) {
            try {
                result = readNewLine(reader);
                if (result.matches("\\d{10}")) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter 10 numbers.");
            }
        }
        return result;
    }

    public Date readBirthday(BufferedReader reader) {
        SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String result = "";
        while(true) {
            try {
                result = readNewLine(reader);
                if (result.matches("^\\d{2}/\\d{2}/\\d{4}")){
                    int day = Integer.parseInt(result.split("/")[0]);
                    int month = Integer.parseInt(result.split("/")[1]);
                    int year = Integer.parseInt(result.split("/")[2]);
                    if (month > 0 && month < 13){
                        if (day > 0 && day < 31){
                            if(year > 1900 && year < 2003) break;
                            else throw new Exception();
                        }
                        else throw new Exception();
                    }
                    else throw new Exception();
                }
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter birthday in format: 01/01/1990");
            }
        }
        Date date = new Date();
        try {
            date = input.parse(result);
        } catch (ParseException e) {
            System.out.println("Something wrong! Date is broken!");
        }
        return date;
    }

    public int readBoolean(BufferedReader reader) {
        int result = 0;
        while(true) {
            try {
                result = readInt(reader);
                if (result==0 || result==1) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter only 0 (if \"No\") or 1 (if \"Yes\").");
            }
        }
        return result;
    }

    public int readPassport(BufferedReader reader) {
        String result;
        while(true) {
            try {
                result = readNewLine(reader);
                if (result.matches("\\d{7}")) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.println("Please enter 7 numbers.");
            }
        }
        return Integer.parseInt(result);
    }
}
