package ui;

import java.util.List;
import java.util.Vector;

import action.CourseActions;

import common.CourseSubject;
import common.Utils;

import exception.ConnectionFailedException;
import exception.RecordNotFoundException;

public class InstructorDialogue {

    public static void addCourse(UI ui) {
        ui.clear();
        ui.write("\nAdding a new course\n");

        try {
            CourseActions ca = new CourseActions(ui.getSession());
            List<CourseSubject> subjects = ca.getAllCourseSubjects();
            Vector<String> choices = new Vector<String>();
            for (CourseSubject cs : subjects) {
                choices.add(cs.getIdCode());
            }

            ui.write("Choose the subject of the course:\n");
            int i = 1;
            for (String s : choices) {
                ui.write(" " + i + ". " + s);
            }

            String rsp = ui.readLine();
            int index = Utils.str2int(rsp) - 1;

            if ((index > 0) && (index < choices.size())) {
                String csIdCode = choices.elementAt(index).toString();
                CourseSubject csSelected = null;

                for (CourseSubject cs : subjects) {
                    if (cs.getIdCode().equals(csIdCode)) {
                        csSelected = cs;
                    }
                }

                if (csSelected != null) {
                    ui.write("Enter the token for the course: ");
                    String token=ui.readLine();
                    

                }
            }

        } catch (ConnectionFailedException e) {
            ui.write("Add course failed due to: Database connection failure.");
            ui.statusUpdate("Add course failed due to: Database connection failure.");
        } catch (RecordNotFoundException e) {
            ui.write("Add course failed due to: Missing course subject information.");
            ui.statusUpdate("Add course failed due to: Missing course subject information.");
        }

    }

    public static void addCourseSubject(UI ui) {
        ui.clear();
        ui.write("\nAdding a new course subject\n");
        ui.write(" Enter the id code for the course: ");
        String idCode = ui.readLine();
        CourseSubject cs = new CourseSubject(idCode);
        ui.write(" Enter the name of the course: ");
        String name = ui.readLine();
        cs.setName(name);

        try {
            CourseActions ca = new CourseActions(ui.getSession());
            ca.addCourseSubject(cs);
            ui.statusUpdate("Adding new course subject");
        } catch (ConnectionFailedException e) {
            ui.write("Add course subject failed due to: Database connection failure.");
            ui.statusUpdate("Add course subject failed due to: Database connection failure.");
        }
    }

    public static void addHomework(UI ui) {

    }

    public static void addTopic(UI ui) {

    }

    public static void findHighestOnAHomework(UI ui) {

    }

    public static void findHighestOverall(UI ui) {

    }

    public static void findMissingHomework(UI ui) {

    }

    public static void showDialogue(UI ui) {
        for (boolean isValid = false; !isValid;) {
            ui.clear();
            ui.write("\nInstructor Activities");
            ui.write("\n");
            ui.write(" 1. Add a course subject\n");
            ui.write(" 2. Add a course\n");
            ui.write(" 3. Add a course topic\n");
            ui.write(" 4. Add a homework\n");
            ui.write(" 5. Find students that did not take a homework\n");
            ui.write(" 6. Find students that scored highest on a homework\n");
            ui.write(" 7. Find students that scored highest on any homework\n");
            ui.write("\n");

            String rsp = ui.readLine();
            try {
                int i = Integer.parseInt(rsp);
                isValid = true;
                switch (i) {
                    case 1:
                }
            } catch (NumberFormatException e) {
                ui.statusUpdate("Invalid selection");
            }
        }
    }
}
