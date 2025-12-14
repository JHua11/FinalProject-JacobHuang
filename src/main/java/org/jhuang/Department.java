package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import util.Util;

@ToString
@EqualsAndHashCode
@Getter
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1;

    /**
     * checks if a department name is valid or not;
     * a department name should only contain letters or space
     * @param departmentName the name to be checked
     * @return whether the name is valid (true) or not (false)
     */
    private static boolean isValidDepartmentName(String departmentName) {
        for (char c : departmentName.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                return false;
            }
        }
        return true;
    }

    public Department(String departmentName) {
        if (isValidDepartmentName(departmentName)) {
            this.departmentName = Util.toTitleCase(departmentName);
            this.departmentId = String.format("D%02d", nextId++);
        } else {
            this.departmentName = null;
            this.departmentId = null;
        }
    }



    public void setDepartmentName(String departmentName) {
        if (isValidDepartmentName(departmentName)) {
            this.departmentName = Util.toTitleCase(departmentName);
        } else {
            System.out.println("Invalid Department Name");
        }
    }
}
