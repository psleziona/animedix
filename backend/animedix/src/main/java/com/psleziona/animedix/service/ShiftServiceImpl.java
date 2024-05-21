package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Shift;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShiftServiceImpl implements ShiftService{
    private final ShiftRepository shiftRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Shift> getShift(Integer idShift) {
        return shiftRepository.findById(idShift);
    }

    @Override
    public List<Shift> getEmployeeShifts(Integer idEmployee) {
        Employee employee = employeeRepository.findById(idEmployee).orElseThrow();
        return employee.getShifts();
    }

    @Override
    public List<Shift> getShiftsByDate(LocalDate date) {
        return shiftRepository.findAll()
                .stream()
                .filter(s -> s.getShiftStart().toLocalDate().equals(date)).toList();
    }

    @Override
    public List<Shift> getShiftsByMonth(LocalDate date) {
        return shiftRepository.findAll()
                .stream()
                .filter(s -> s.getShiftStart().getMonth() == date.getMonth())
                .sorted((o1, o2) -> o1.getShiftStart().isBefore(o2.getShiftStart()) ? -1 : 1).toList();
    }

    @Override
    public Shift setShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public void deleteShift(Integer idShift) {
        shiftRepository.deleteById(idShift);
    }

    @Override
    public void generateEmployeeShifts(Map<String, String> shiftInfo) {
        LocalDate date = LocalDate.parse(shiftInfo.get("month") + "-01", DateTimeFormatter.ofPattern("yyyy-MM-d"));
        shiftRepository.findAll().stream().filter(shift -> shift.getShiftStart().getMonth().equals(date.getMonth()))
                        .findFirst().or(() -> {
                                        generateShiftsForMonth(date);
                                        return Optional.of(new Shift());
                });

        shiftInfo.entrySet().stream()
                .filter(e -> !e.getKey().equals("month"))
                .forEach(e -> {
                    Integer idEmployee = Integer.parseInt(e.getKey());
                    employeeRepository.findById(idEmployee).ifPresent(employee -> {
                        if(!Objects.equals(e.getValue(), "")) {
                            int requiredEmployeeHours = Integer.parseInt(e.getValue());
                            long employeeWorkingHours = employee.getShifts().stream().filter(shift -> shift.getShiftStart().getMonth().equals(date.getMonth()))
                                    .count() * 8;
                            int hourDiff = (int) (requiredEmployeeHours - employeeWorkingHours);
                            long dayNo = Math.abs(Math.round(hourDiff / 8.0));
                            Random random = new Random();

                            if(hourDiff > 0)
                                addEmployeeShifts(employee, date, dayNo, random);
                            else
                                removeExceedEmployeeShifts(employee, date, dayNo, random);
                        }
                    });
                });
    }

    @Override
    public void generateShiftsForMonth(LocalDate date) {
        int numberOfDays = getMonthDaysCount(date);
        for(int i = 1; i <= numberOfDays;i++) {
            LocalDateTime shiftStart = LocalDateTime.of(date.getYear(), date.getMonth(), i, 8,0,0);
            LocalDateTime shiftEnd = LocalDateTime.of(date.getYear(), date.getMonth(), i, 16,0,0);
            Shift s = new Shift();
            s.setEmployees(new ArrayList<>());
            s.setShiftStart(shiftStart);
            s.setShiftEnd(shiftEnd);
            shiftRepository.save(s);
        }
    }

    private int getMonthDaysCount(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        return yearMonth.lengthOfMonth();
    }

    private void removeExceedEmployeeShifts(Employee employee, LocalDate date, long dayNo, Random random) {
        List<Shift> shifts = employee.getShifts().stream()
                .filter(shift -> shift.getShiftStart().getMonth().equals(date.getMonth())).collect(Collectors.toList());

        for (int i = 0; i < dayNo; i++) {
            if (shifts.isEmpty()) break;

            int index = random.nextInt(shifts.size());
            Shift s = shifts.get(index);

            List<Employee> employees = s.getEmployees().stream()
                    .filter(employee1 -> !Objects.equals(employee1.getId(), employee.getId())).collect(Collectors.toList());
            s.setEmployees(employees);
            shiftRepository.save(s);
            shifts.remove(index);
        }
        employee.setShifts(shifts);
        employeeRepository.save(employee);
    }

    private void addEmployeeShifts(Employee employee, LocalDate date, long dayNo, Random random) {
        int numberOfDays = getMonthDaysCount(date);
        Set<Integer> workingDaysInMonth = new HashSet<>();

        while (workingDaysInMonth.size() <= dayNo) {
            int d = random.nextInt(numberOfDays) + 1;
            workingDaysInMonth.add(d);
        }

        for(int day : workingDaysInMonth) {
            shiftRepository.findAll().stream()
                    .filter(s -> Objects.equals(s.getShiftStart().toLocalDate(), LocalDate.of(date.getYear(), date.getMonth(), day))).findFirst()
                    .ifPresent(shift -> {
                        employee.getShifts().add(shift);
                        List<Employee> employeeList = shift.getEmployees();
                        if (employeeList == null)
                            employeeList = new ArrayList<>();
                        employeeList.add(employee);
                        shift.setEmployees(employeeList);
                        shiftRepository.save(shift);
                    });
        }
        employeeRepository.save(employee);
    }
}
