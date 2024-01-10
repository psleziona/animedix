package com.psleziona.animedix.service;

import com.psleziona.animedix.model.DoctorTerm;
import com.psleziona.animedix.model.Visit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DoctorTermService {
    private final EmployeeService employeeService;
    private final VisitService visitService;

    List<DoctorTerm> getAvailableTerms(LocalDate dayFrom,
                                              LocalDate dayTo,
                                              LocalTime hourFrom,
                                              LocalTime hourTo) {
        List<LocalTime> availableVisitHours = List.of(LocalTime.of(8,0), LocalTime.of(8,30), LocalTime.of(9,0),
                LocalTime.of(9,30), LocalTime.of(10,0), LocalTime.of(10,30), LocalTime.of(11,0), LocalTime.of(11,30),
                LocalTime.of(12,0), LocalTime.of(12,30), LocalTime.of(13,0), LocalTime.of(13,30), LocalTime.of(14,0),
                LocalTime.of(14,30), LocalTime.of(15,0), LocalTime.of(15,30));

        var availableTerms = employeeService.getEmployees()
            .stream()
            .filter(e -> e.getShifts()
                    .stream()
                    .anyMatch(s -> s.getShiftStart().isAfter(dayFrom.atTime(7,59))
                            && s.getShiftEnd().isBefore(dayTo.atTime(16,0))))
                .map(e -> {
                    var possibleVisitTermsUnfiltered = e.getShifts().stream()
                            .map(s -> {
                                var day = s.getShiftStart().toLocalDate();
                                List<LocalDateTime> temp = new ArrayList<>();
                                for(LocalTime hour : availableVisitHours) {
                                    if(hour.isAfter(hourFrom) && hour.isBefore(hourTo))
                                        temp.add(LocalDateTime.of(day, hour));
                                }
                                return temp;
                            })
                            .flatMap(List::stream)
                            .toList();
                    return new DoctorTerm(e, possibleVisitTermsUnfiltered);

                }).toList();
        availableTerms.stream()
                .map(dt -> {
                    var doctorVisits = dt.getDoctor().getVisits().stream()
                            .filter(v -> (v.getDate().isAfter(LocalDateTime.of(dayFrom,hourFrom)) && v.getDate().isBefore(LocalDateTime.of(dayTo, hourTo))))
                            .map(Visit::getDate).toList();
                    var possibleVisitsTerm = dt.getAvailableTerms()
                            .stream().filter(localDateTime -> !doctorVisits.contains(localDateTime)).toList();
                    dt.setAvailableTerms(possibleVisitsTerm);
                    return dt;
                }).toList();
        return availableTerms;

    }
}
