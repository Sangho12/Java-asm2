import java.io.*;
import java.util.*;

public class student_admission_record {
    public String Name;
    public double Age;
    public String Gender;
    public double Score;
    public double Percentage;
    public String City;
    public boolean Isaccepted;

    public String getName() {
        return Name;
    }

    public double getAge() {
        return Age;
    }

    public String getGender() {
        return Gender;
    }

    public double getScore() {
        return Score;
    }

    public double getPercentage() {
        return Percentage;
    }

    public String getCity() {
        return City;
    }

    public boolean isIsaccepted() {
        return Isaccepted;
    }

    public String getIsaccepted() {
        if (isIsaccepted()){
            return "Accepted";
        }
        return "Rejected";
    }

    public student_admission_record (String Name, double Age, String Gender, double Score, double Percentage, String City, boolean Isaccepted) {
        this.Name = Name;
        this.Age = Age;
        this.Gender = Gender;
        this.Score = Score;
        this.Percentage = Percentage;
        this.City = City;
        this.Isaccepted = Isaccepted;

        if (this.Age < 0) throw new IllegalArgumentException("Age cannot be less than 0");
        if (this.Score < 0) throw new IllegalArgumentException("Admission Test score cannot be less than 0");
        if (this.Percentage < 0) throw new IllegalArgumentException("High school percentage cannot be less than 0");
    }

    @Override
    public String toString() {
        return getName() + "," + getAge() + "," + getGender() + "," + getScore() + "," + getPercentage() + "," + getCity() + "," + getIsaccepted();
    }

    public static ArrayList<student_admission_record> read(File f) {
        String Name, Gender, City;
        double Age, Score, Percentage;
        boolean Isaccepted = false;
        student_admission_record a;
        ArrayList<student_admission_record> student_admission_records = new ArrayList<student_admission_record>();

        try {
            Scanner in = new Scanner(f);
            in.nextLine(); //Skip header
            int Linecount = 1;
            while (in.hasNextLine()){
                String line = in.nextLine();
                Linecount++;
                boolean continues = false;
                Scanner token = new Scanner(line);
                token.useDelimiter(",");
                Dictionary<String, String> dict = new Hashtable<>();
                ArrayList<String> Tokenlist = new ArrayList<String>(Arrays.asList("Name", "Age", "Gender", "Score", "Percentage", "City", "Isaccepted"));
                //Check if there are empty value in the line
                for (int i = 0; i < Tokenlist.size(); i++) {
                    if (token.hasNext()) {
                        String tokens = token.next();
                        if (tokens.isEmpty()) {
                            continues = true;
                            break;
                        }
                        dict.put(Tokenlist.get(i), tokens);
                    } else { //If the last element is missing, token.hasNex() will return false
                        continues = true;
                        break;
                    }
                }
                if (continues) {
                    System.out.println("Line " + Linecount + " (Empty value) : " + line);
                    continue;
                }
                //Check negative value for double parameter
                if (Double.parseDouble(dict.get("Age")) < 0 || Double.parseDouble(dict.get("Score")) < 0 || Double.parseDouble(dict.get("Percentage")) < 0){
                    System.out.println("Line " + Linecount + " (Negative value) : " + line);
                    continue;
                }
                switch (dict.get("Isaccepted")) {
                    case "Accepted" -> Isaccepted = true;
                    case "Rejected" -> Isaccepted = false;
                    default -> continues = true;
                }
                //Check for admission status, it is handled separately because it is a boolean parameter
                if (continues) {
                    System.out.println("Line " + Linecount + " (Invalid admission status) : " + line);
                    continue;
                }
                //Casting the object parameters
                Name = dict.get("Name");
                Age = Double.parseDouble(dict.get("Age"));
                Gender = dict.get("Gender");
                Score = Double.parseDouble(dict.get("Score"));
                Percentage = Double.parseDouble(dict.get("Percentage"));
                City = dict.get("City");
                a = new student_admission_record(Name, Age, Gender, Score, Percentage, City, Isaccepted);
                student_admission_records.add(a);
            }
            System.out.println("\n***" + student_admission_records.size() + " students passed the validation.");
            return student_admission_records;
        } catch (FileNotFoundException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write (ArrayList<student_admission_record> student_admission_records) throws IOException {
        String Filename = "Default";
        File f = new File(System.getProperty("user.dir") + "/" + Filename + ".csv");
        PrintWriter out = new PrintWriter(new FileOutputStream(f));
        //Print the header to the file first
        out.println("Name,Age,Gender,Admission Test Score,High School Percentage,City,Admission Status");
        for (student_admission_record stu : student_admission_records){
            out.println(stu);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<student_admission_record> a = student_admission_record.read(new File("/Users/Sangho/Desktop/Java/asm2/student_admission_record_dirty.csv"));
        student_admission_record.write(a);
    }
}