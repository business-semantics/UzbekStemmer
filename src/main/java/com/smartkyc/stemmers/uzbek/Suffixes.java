package com.smartkyc.stemmers.uzbek;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Suffixes
{
	@JacksonXmlProperty(localName = "suffixes")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Suffix> suffixes = new ArrayList<>();

	@Getter
	@Setter
	public static class Suffix
	{
		@JacksonXmlProperty(isAttribute = true, localName = "class")
		private String suffixClass;

		@JacksonXmlProperty(isAttribute = true, localName = "classId")
		private String classId;

		@JacksonXmlProperty(localName = "element")
		@JacksonXmlElementWrapper(useWrapping = false)
		private List<Element> elements = new ArrayList<>();

		@Getter
		@Setter
		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Element
		{
			@JacksonXmlProperty(isAttribute = true, localName = "id")
			private String id;

			@JacksonXmlProperty(localName = "suffix")
			private ElementSuffix elementSuffix;

			@JacksonXmlProperty(localName = "join")
			private Join join;

			@JacksonXmlProperty(localName = "pass")
			private Pass pass;

			@Getter
			@Setter
			public static class ElementSuffix
			{
				@JacksonXmlProperty(isAttribute = true, localName = "allomorphAttr")
				private boolean allomorphAtribute;

				@JacksonXmlText
				private String suffixValue;

				@JacksonXmlProperty(localName = "allomorph")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Allomorph> allomorphList = new ArrayList<>();

				@Getter
				@Setter
				public static class Allomorph
				{
					@JacksonXmlText
					private String allomorphValue;
				}
			}

			@Getter
			@Setter
			public static class Join
			{
				@JacksonXmlProperty(localName = "br")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Br> brList = new ArrayList<>();
			}

			@Getter
			@Setter
			public static class Pass
			{
				@JacksonXmlProperty(localName = "br")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Br> brList = new ArrayList<>();

			}

			@Getter
			@Setter
			public static class Br
			{
				@JacksonXmlText
				private String brText;
			}
		}
	}
}
