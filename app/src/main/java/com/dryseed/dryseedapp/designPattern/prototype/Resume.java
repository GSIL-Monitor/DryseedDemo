package com.dryseed.dryseedapp.designPattern.prototype;

/**
 * Created by caiminming on 2017/1/23.
 */
public class Resume implements Cloneable {

    private String name;
    private String sex;
    private String age;
    private WorkExperience work;

    public Resume() {
        this.work = new WorkExperience();
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    class WorkExperience{
        private String workDate;
        private String workCompany;

        public String getWorkDate() {
            return workDate;
        }

        public void setWorkDate(String workDate) {
            this.workDate = workDate;
        }

        public String getWorkCompany() {
            return workCompany;
        }

        public void setWorkCompany(String workCompany) {
            this.workCompany = workCompany;
        }

        @Override
        public String toString() {
            return "WorkExperience{" +
                    "workDate='" + workDate + '\'' +
                    ", workCompany='" + workCompany + '\'' +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public WorkExperience getWork() {
        return work;
    }

    public void setWork(String workDate, String workCompany) {
        this.work.workDate = workDate;
        this.work.workCompany = workCompany;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", work=" + work +
                '}';
    }
}
