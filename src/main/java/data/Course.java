package data;

import services.PublicHolidayService;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Course {
    private String name;
    private int eap;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Teacher teacher;

    private PublicHolidayService publicHolidayService = new PublicHolidayService();

    public Course() {
    }

    public Course(String name, int eap, ZonedDateTime startDate, ZonedDateTime endDate, Teacher teacher) {
        this.name = name;
        this.eap = eap;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEap() {
        return eap;
    }

    public void setEap(int eap) {
        this.eap = eap;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public long getWorkingDays() {
        if (endDate.isAfter(startDate)) {

            final int startW = startDate.getDayOfWeek().getValue();
            final int endW = endDate.getDayOfWeek().getValue();

            try {
                final long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
                long result = days - 2*(days/7); //remove weekends

                if (days % 7 != 0) { //deal with the rest days
                    if (startW == 7) {
                        result -= 1;
                    } else if (endW == 7) {  //they can't both be Sunday, otherwise rest would be zero
                        result -= 1;
                    } else if (endW < startW) { //another weekend is included
                        result -= 2;
                    }
                }


                return result;

            } catch (Exception ex) {
                throw new IllegalArgumentException("eieiei data");
            }

        } else {
            throw new IllegalArgumentException("oih");
        }
    }


    public long getLength(){
        PublicHolidayService publicHolidayService = new PublicHolidayService();
        long schoolDays = getWorkingDays() - publicHolidayService.getPublicHolidaysOnWorkdays(startDate,endDate);
        return schoolDays;
    }


}
