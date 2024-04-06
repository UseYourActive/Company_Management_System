import com.tinqin.cms.entities.Leave;
import com.tinqin.cms.enums.LeaveStatus;
import com.tinqin.cms.enums.LeaveType;
import com.tinqin.cms.operations.FindAllLeavesOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestDataUtil {

    public static Page<Leave> createMockLeavePage() {
        List<Leave> leaves = new ArrayList<>();
        leaves.add(createMockLeave());
        return new PageImpl<>(leaves);
    }

    public static Leave createMockLeave() {
        Leave leave = new Leave();
        leave.setId(UUID.randomUUID());
        leave.setEmployeeId(UUID.randomUUID());
        leave.setStartDate(LocalDate.now());
        leave.setEndDate(LocalDate.now().plusDays(3));
        leave.setLeaveType(LeaveType.ANNUAL);
        leave.setStatus(LeaveStatus.APPROVED);
        return leave;
    }

    public static List<FindAllLeavesOperation.FindAllLeavesResponseDTO> createMockDTOList() {
        List<FindAllLeavesOperation.FindAllLeavesResponseDTO> dtoList = new ArrayList<>();
        FindAllLeavesOperation.FindAllLeavesResponseDTO dto = FindAllLeavesOperation.FindAllLeavesResponseDTO.builder()
                .id("1")
                .employeeId("employeeId")
                .startDate("2024-03-31")
                .endDate("2024-04-03")
                .leaveType("PAID_LEAVE")
                .status("APPROVED")
                .build();
        dtoList.add(dto);
        return dtoList;
    }
}
