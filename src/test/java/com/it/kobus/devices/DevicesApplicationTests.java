package com.it.kobus.devices;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.kobus.devices.logic.dto.DeviceDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DevicesApplicationTests {

	public static final String DEV_NAME_1 = "Router";
	public static final String BRAND_1 = "Huawei";
	public static final String AVAILABLE = "AVAILABLE";
	public static final String DEV_NAME_2 = "Alpha";
	public static final String BRAND_2 = "TestBrand";
	public static final String INACTIVE = "INACTIVE";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void createDevice_AndFetchById() throws Exception {
		DeviceDTO device = new DeviceDTO();
		device.name = DEV_NAME_1;
		device.brand = BRAND_1;
		device.state = AVAILABLE;
		device.created = LocalDateTime.now();

		String body = objectMapper.writeValueAsString(device);

		String response = mockMvc.perform(post("/device")
						.contentType(MediaType.APPLICATION_JSON)
						.content(body))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andReturn().getResponse().getContentAsString();

		DeviceDTO created = objectMapper.readValue(response, DeviceDTO.class);

		// GET /device/{id}
		mockMvc.perform(get("/device/" + created.id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value(DEV_NAME_1))
				.andExpect(jsonPath("$.brand").value(BRAND_1));
	}

	@Test
	void updateDevice_ShouldNotChangeCreated() throws Exception {
		DeviceDTO created = createDevice(DEV_NAME_2, BRAND_2, AVAILABLE);

		DeviceDTO update = new DeviceDTO();
		update.name = DEV_NAME_2;
		update.brand = BRAND_2;
		update.state = INACTIVE;
		update.created = LocalDateTime.now().plusYears(1);

		mockMvc.perform(put("/device/{id}", created.id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.created").value(created.created.withNano(0).toString()));
	}

	@Test
	void updateInUseDevice_ShouldNotChangeNameOrBrand() throws Exception {
		DeviceDTO created = createDevice("MyMouse", "MyszyCorp", "IN_USE");

		DeviceDTO update = new DeviceDTO();
		update.name = "Hacked";
		update.brand = "OtherBrand";
		update.state = "IN_USE";
		update.created = created.created;

		mockMvc.perform(put("/device/{id}", created.id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(update)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("MyMouse"))
				.andExpect(jsonPath("$.brand").value("MyszyCorp"));
	}

	@Test
	void deleteDevice_ShouldSucceed_WhenNotInUse() throws Exception {
		DeviceDTO created = createDevice("ToDelete", "BrandX", "AVAILABLE");

		mockMvc.perform(delete("/device/{id}", created.id))
				.andExpect(status().isNoContent());
	}

	@Test
	void deleteDevice_ShouldFail_WhenInUse() throws Exception {
		DeviceDTO created = createDevice("ToProtect", "BrandY", "IN_USE");

		mockMvc.perform(delete("/device/{id}", created.id))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message").value("Cannot delete a device that is in use."));
	}

	private DeviceDTO createDevice(String name, String brand, String state) throws Exception {
		DeviceDTO device = new DeviceDTO();
		device.name = name;
		device.brand = brand;
		device.state = state;
		device.created = LocalDateTime.now();

		String response = mockMvc.perform(post("/device")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(device)))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		return objectMapper.readValue(response, DeviceDTO.class);
	}
}