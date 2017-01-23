package com.dryseed.dryseedapp.designPattern.prototype;

/**
 * Created by caiminming on 2017/1/23.
 */
public class ResumeDeepCopy implements Cloneable {

    private String name;
    private String sex;
    private String age;
    private WorkExperience work;

    public ResumeDeepCopy() {
        this.work = new WorkExperience();
    }

    public Object clone() {
        try {
            ResumeDeepCopy resume = (ResumeDeepCopy) super.clone();
            resume.work = (WorkExperience) work.clone();
            return resume;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    class WorkExperience implements Cloneable{
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
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
