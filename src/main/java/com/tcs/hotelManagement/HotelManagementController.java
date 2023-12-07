package com.tcs.hotelManagement;

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
	public ResponseEntity<?> getPaymentById(@PathVariable Long id) {
		HotelManagement hotelManagementById = hotelManagementRepository.getReferenceById(id);
		HotelManagement hotelManagementDto = new HotelManagement(hotelManagementById.getId(),
				hotelManagementById.getName(), hotelManagementById.getRoomNumber(), hotelManagementById.getStatus());
		return ResponseEntity.ok(hotelManagementDto);
	}
}