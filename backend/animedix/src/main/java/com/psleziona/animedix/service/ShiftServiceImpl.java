package com.psleziona.animedix.service;

import com.psleziona.animedix.model.Employee;
import com.psleziona.animedix.model.Shift;
import com.psleziona.animedix.repository.EmployeeRepository;
import com.psleziona.animedix.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public void generateShifts(Map<String, String> shiftInfo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate date = LocalDate.parse(shiftInfo.get("month") + "-01", formatter);
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        int numberOfDays = yearMonth.lengthOfMonth();
        for(int i = 1; i <= numberOfDays;i++) {
            LocalDateTime shiftStart = LocalDateTime.of(date.getYear(), date.getMonth(), i, 8,0,0);
            LocalDateTime shiftEnd = LocalDateTime.of(date.getYear(), date.getMonth(), i, 16,0,0);
            Shift s = new Shift();
            s.setShiftStart(shiftStart);
            s.setShiftEnd(shiftEnd);
            shiftRepository.save(s);
        }
        shiftInfo.entrySet().stream()
                .filter(e -> !e.getKey().equals("month"))
                .forEach(e -> {
                    Integer idE = Integer.parseInt(e.getKey());
                    Integer hoursNo = Integer.parseInt(e.getValue());
                    Employee employee = employeeRepository.findById(idE).get();
                    Long dayNo = Math.round(hoursNo / 8.0);

                    Set<Integer> monthDays = new HashSet<>();
                    Random random = new Random();

                    while (monthDays.size() <= dayNo) {
                        int d = random.nextInt(numberOfDays) + 1;
                        monthDays.add(d);
                    }

                    for(int day : monthDays) {
                        Shift shift = shiftRepository.findAll().stream()
                                .filter(s -> Objects.equals(s.getShiftStart().toLocalDate(), LocalDate.of(date.getYear(), date.getMonth(), day))).findFirst().get();
                        employee.getShifts().add(shift);
                        List<Employee> employeeList = shift.getEmployees();
                        if (employeeList == null)
                            employeeList = new ArrayList<>();
                        employeeList.add(employee);
                        shift.setEmployees(employeeList);
                        shiftRepository.save(shift);
                    }
                    employeeRepository.save(employee);
                });
    }
}
