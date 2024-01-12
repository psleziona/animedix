package com.psleziona.animedix.service;

import com.psleziona.animedix.auth.AuthService;
import com.psleziona.animedix.model.AnimalSurgery;
import com.psleziona.animedix.model.DoctorTerm;
import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Visit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorTermService {
    private final EmployeeService employeeService;
    private final AuthService authService;
    private static final List<LocalTime> availableVisitHours = List.of(LocalTime.of(8,0), LocalTime.of(8,30), LocalTime.of(9,0),
            LocalTime.of(9,30), LocalTime.of(10,0), LocalTime.of(10,30), LocalTime.of(11,0), LocalTime.of(11,30),
            LocalTime.of(12,0), LocalTime.of(12,30), LocalTime.of(13,0), LocalTime.of(13,30), LocalTime.of(14,0),
            LocalTime.of(14,30), LocalTime.of(15,0), LocalTime.of(15,30));

    public List<DoctorTerm> getAvailableTerms(LocalDate dayFrom,
                                              LocalDate dayTo,
                                              LocalTime hourFrom,
                                              LocalTime hourTo) {
        LocalDate currentDate = LocalDate.now();
        if(dayFrom.isBefore(currentDate))
            dayFrom = currentDate;

        LocalDate finalDayFrom = dayFrom;
        var availableTerms = employeeService.getEmployees()
            .stream()
            .filter(e -> e.getShifts()
                    .stream()
                    .anyMatch(s -> s.getShiftStart().isAfter(finalDayFrom.atTime(7,59))
                            && s.getShiftEnd().isBefore(dayTo.atTime(16,1))))

            .map(e -> {
                var possibleVisitTermsUnfiltered = e.getShifts().stream()
                        .filter(s -> s.getShiftStart().isAfter(finalDayFrom.atTime(7,59)) && s.getShiftEnd().isBefore(dayTo.atTime(16,1)))
                        .sorted(Comparator.comparing(s -> s.getShiftStart().toLocalDate()))
                        .map(s -> {
                            var day = s.getShiftStart().toLocalDate();
                            List<LocalDateTime> temp = new ArrayList<>();
                            LocalDateTime currentTime = LocalDateTime.now();
                            for(LocalTime hour : availableVisitHours) {
                                if(hour.isAfter(hourFrom.minusMinutes(1)) && hour.isBefore(hourTo.plusMinutes(1)) &&
                                        LocalDateTime.of(day,hour).isAfter(currentTime))
                                    temp.add(LocalDateTime.of(day, hour));
                            }
                            return temp;
                        })
                        .flatMap(List::stream)
                        .toList();
                return new DoctorTerm(e, possibleVisitTermsUnfiltered);

            })
            .map(dt -> {
                var doctorVisits = dt.getDoctor().getVisits().stream()
                        .filter(v -> (v.getDate().isAfter(LocalDateTime.of(finalDayFrom,hourFrom)) && v.getDate().isBefore(LocalDateTime.of(dayTo, hourTo))))
                        .map(Visit::getDate).toList();

                var doctorSurgeries = dt.getDoctor().getSurgeries().stream()
                        .filter(s -> (s.getDate().isAfter(LocalDateTime.of(finalDayFrom,hourFrom)) && s.getDate().isBefore(LocalDateTime.of(dayTo, hourTo))))
                        .map(AnimalSurgery::getDate).toList();

                var possibleVisitsTerm = dt.getAvailableTerms()
                        .stream().filter(localDateTime -> !doctorVisits.contains(localDateTime) && !doctorSurgeries.contains(localDateTime)).toList();
                dt.setAvailableTerms(possibleVisitsTerm);
                return dt;
            }).toList();
        return availableTerms;
    }

    public Optional<DoctorTerm> getDoctorAvailableTerms(LocalDate dayFrom,
                                                        LocalDate dayTo) {
        Employee employee = (Employee) authService.getSessionUser();
        var possibleVisitTermsUnfiltered = employee.getShifts().stream()
                .filter(s -> s.getShiftStart().isAfter(dayFrom.atTime(7,59)) && s.getShiftEnd().isBefore(dayTo.atTime(16,1)))
                .sorted(Comparator.comparing(s -> s.getShiftStart().toLocalDate()))
                .map(s -> {
                    var day = s.getShiftStart().toLocalDate();
                    List<LocalDateTime> temp = new ArrayList<>();
                    LocalDateTime currentTime = LocalDateTime.now();
                    for(LocalTime hour : availableVisitHours) {
                        if(LocalDateTime.of(day,hour).isAfter(currentTime))
                            temp.add(LocalDateTime.of(day, hour));
                    }
                    return temp;
                })
                .flatMap(List::stream)
                .toList();

        var doctorVisits = employee.getVisits().stream()
                .filter(v -> (v.getDate().isAfter(dayFrom.atStartOfDay()) && v.getDate().isBefore(dayTo.atStartOfDay().plusHours(24))))
                .map(Visit::getDate).toList();

        var doctorSurgeries = employee.getSurgeries().stream()
                .filter(s ->  (s.getDate().isAfter(dayFrom.atStartOfDay()) && s.getDate().isBefore(dayTo.atStartOfDay().plusHours(24))))
                .map(AnimalSurgery::getDate).toList();

        var possibleVisitsTerm = possibleVisitTermsUnfiltered
                .stream().filter(localDateTime -> !doctorVisits.contains(localDateTime) && !doctorSurgeries.contains(localDateTime)).toList();

        return Optional.of(new DoctorTerm(employee, possibleVisitsTerm));
    }
}
