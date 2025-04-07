package data.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotBubble {
	private String type;
	private String text;
	private String title;
	private String description;
	private String coverImage;
	private List<Button> buttons;
	private List<List<String>> contentTable;
}
