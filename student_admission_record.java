import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

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
    }

    @Override
    public String toString() {
        return getName() + "," + getAge() + "," + getGender() + "," + getScore() + "," + getPercentage() + "," + getCity() + "," + getIsaccepted();
    }

    public static ArrayList<student_admission_record> read(File f) throws FileNotFoundException {
        String Name, Gender, City;
        double Age, Score, Percentage;
        boolean Isaccepted;
        student_admission_record a;
        ArrayList<student_admission_record> student_admission_records = new ArrayList<student_admission_record>();

        Scanner in = new Scanner(f);
        in.nextLine(); //Skip header
        //while (in.hasNextLine()){
        for (int i = 0; i < 2; i ++) {
            String line = in.nextLine();
            Scanner token = new Scanner(line);
            token.useDelimiter(",");
            Name = token.next();
            Age = Double.parseDouble(token.next());
            Gender = token.next();
            Score = Double.parseDouble(token.next());
            Percentage = Double.parseDouble(token.next());
            City = token.next();
            String status = token.next();
            switch (status) {
                case "Accepted" -> Isaccepted = true;
                case "Rejected" -> Isaccepted = false;
                default -> throw new IllegalArgumentException("Invalid admission status");
            }
            a = new student_admission_record(Name, Age, Gender, Score, Percentage, City, Isaccepted);
            student_admission_records.add(a);
            // }
        }
        return student_admission_records;
    }


    public static void write (ArrayList<student_admission_record> student_admission_records) throws IOException {
        File f = new File(System.getProperty("user.dir") + "/test.csv");
        PrintWriter out = new PrintWriter(new FileOutputStream(f));
        if (!f.exists()){
            f.createNewFile();
        }
        for (student_admission_record stu : student_admission_records){
            out.println(stu);
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<student_admission_record> a = student_admission_record.read(new File("/Users/Sangho/Desktop/Java/asm2/student_admission_record_dirty.csv"));
        for (student_admission_record stu : a){
            System.out.println(stu);
        }
        student_admission_record.write(a);
    }
}
