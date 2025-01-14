  /**
* Copyright © The Bank of New York Mellon 2021
* Licensed under the BSD-3-Clause License (the “License”);
* You may not use this file except in compliance with the License.
* You may obtain a copy of the License at:
* https://opensource.org/licenses/BSD-3-Clause
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES ARE DISCLAIMED.  SEE THE LICENSE FOR FURTHER RESTRICTIONS.
*/

package bnymellon.codekatas.calendarkata;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Objects;

import org.threeten.extra.Interval;

public class Meeting implements Comparable<Meeting>
{
    public static final Comparator<Meeting> COMPARATOR = Comparator.comparing(Meeting::getDate).thenComparing(Meeting::getStartTime);
    private String subject;
    private LocalDate date;
    private LocalTime startTime;
    private Duration duration;
    private ZoneId zoneId;

    public Meeting(String subject, LocalDate date, LocalTime startTime, Duration duration, ZoneId zoneId)
    {
        this.subject = subject;
        this.date = date;
        this.startTime = startTime;
        this.duration = duration;
        this.zoneId = zoneId;
    }

    public boolean overlaps(Interval interval)
    {
        return this.getInterval().overlaps(interval);
    }

    public String getSubject()
    {
        return this.subject;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public LocalTime getStartTime()
    {
        return this.startTime;
    }

    public Duration getDuration()
    {
        return this.duration;
    }

    public ZoneId getZoneId()
    {
        return this.zoneId;
    }

    public LocalTime getEndTime()
    {
        return this.getStartTime().plus(this.getDuration());
    }

    public Interval getInterval()
    {
        return Interval.of(
                LocalDateTime.of(this.date,this.startTime)
                        .atZone(this.zoneId)
                        .toInstant(),
                this.duration);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || this.getClass() != o.getClass())
        {
            return false;
        }
        Meeting meeting = (Meeting) o;
        return Objects.equals(this.getSubject(), meeting.getSubject()) &&
                Objects.equals(this.getDate(), meeting.getDate()) &&
                Objects.equals(this.getStartTime(), meeting.getStartTime()) &&
                Objects.equals(this.getDuration(), meeting.getDuration()) &&
                Objects.equals(this.getZoneId(), meeting.getZoneId());
    }

    @Override
    public int hashCode()
    {
        int result = this.getSubject().hashCode();
        result = 31 * result + this.getDate().hashCode();
        result = 31 * result + this.getStartTime().hashCode();
        result = 31 * result + this.getDuration().hashCode();
        result = 31 * result + this.getZoneId().hashCode();
        return result;
    }

    @Override
    public String toString()
    {
        return "Meeting(" +
                "subject='" + this.getSubject() + '\'' +
                ", date=" + this.getDate() +
                ", startTime=" + this.getStartTime() +
                ", duration=" + this.getDuration() +
                ", endTime=" + this.getEndTime() +
                ')';
    }

    @Override
    public int compareTo(Meeting that)
    {
        return COMPARATOR.compare(this, that);
    }
}
