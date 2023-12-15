package com.tcs.hotelManagement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hotelManagements")
public class HotelManagementController {
	private HotelManagementRepository hotelManagementRepository;

	public HotelManagementController(HotelManagementRepository hotelManagementRepository) {
		this.hotelManagementRepository = hotelManagementRepository;
	}

	@PostMapping
	public ResponseEntity<?> makePayment(@RequestBody HotelManagement hotelManagement) {
		return ResponseEntity.ok(hotelManagementRepository.save(hotelManagement));
	}

	@GetMapping("/{id}")
	public ResponseEntity<HotelManagement> isHotelIdPresent(@PathVariable Long id) {
		Optional<HotelManagement> hotelManagementById = hotelManagementRepository.findById(id);
		if (hotelManagementById.isPresent()) {
			HotelManagement hotelManagement = hotelManagementById.get();
			HotelManagement hotelManagementDto = new HotelManagement(hotelManagement.getId(), hotelManagement.getName(),
					hotelManagement.getRoomNumber(), hotelManagement.getStatus());
			return ResponseEntity.ok(hotelManagementDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
