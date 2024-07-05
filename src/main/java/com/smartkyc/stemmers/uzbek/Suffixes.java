package com.smartkyc.stemmers.uzbek;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.util.ArrayList;
import java.util.List;

public class Suffixes
{
	@JacksonXmlProperty(localName = "suffixes")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Suffix> suffixes = new ArrayList<>();

	public List<Suffix> getSuffixes()
	{
		return this.suffixes;
	}

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "suffixes")
	public void setSuffixes(final List<Suffix> suffixes)
	{
		this.suffixes = suffixes;
	}

	public static class Suffix
	{
		@JacksonXmlProperty(isAttribute = true, localName = "class")
		private String suffixClass;

		@JacksonXmlProperty(isAttribute = true, localName = "classId")
		private String classId;

		@JacksonXmlProperty(localName = "element")
		@JacksonXmlElementWrapper(useWrapping = false)
		private List<Element> elements = new ArrayList<>();

		public String getSuffixClass()
		{
			return this.suffixClass;
		}

		public String getClassId()
		{
			return this.classId;
		}

		public List<Element> getElements()
		{
			return this.elements;
		}

		@JacksonXmlProperty(isAttribute = true, localName = "class")
		public void setSuffixClass(final String suffixClass)
		{
			this.suffixClass = suffixClass;
		}

		@JacksonXmlProperty(isAttribute = true, localName = "classId")
		public void setClassId(final String classId)
		{
			this.classId = classId;
		}

		@JacksonXmlElementWrapper(useWrapping = false)
		@JacksonXmlProperty(localName = "element")
		public void setElements(final List<Element> elements)
		{
			this.elements = elements;
		}

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

			public String getId()
			{
				return this.id;
			}

			public ElementSuffix getElementSuffix()
			{
				return this.elementSuffix;
			}

			public Join getJoin()
			{
				return this.join;
			}

			public Pass getPass()
			{
				return this.pass;
			}

			@JacksonXmlProperty(isAttribute = true, localName = "id")
			public void setId(final String id)
			{
				this.id = id;
			}

			@JacksonXmlProperty(localName = "suffix")
			public void setElementSuffix(final ElementSuffix elementSuffix)
			{
				this.elementSuffix = elementSuffix;
			}

			@JacksonXmlProperty(localName = "join")
			public void setJoin(final Join join)
			{
				this.join = join;
			}

			@JacksonXmlProperty(localName = "pass")
			public void setPass(final Pass pass)
			{
				this.pass = pass;
			}

			public static class ElementSuffix
			{
				@JacksonXmlProperty(isAttribute = true, localName = "allomorphAttr")
				private boolean allomorphAtribute;

				@JacksonXmlText
				private String suffixValue;

				@JacksonXmlProperty(localName = "allomorph")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Allomorph> allomorphList = new ArrayList<>();

				public boolean isAllomorphAtribute()
				{
					return this.allomorphAtribute;
				}

				public String getSuffixValue()
				{
					return this.suffixValue;
				}

				public List<Allomorph> getAllomorphList()
				{
					return this.allomorphList;
				}

				@JacksonXmlProperty(isAttribute = true, localName = "allomorphAttr")
				public void setAllomorphAtribute(final boolean allomorphAtribute)
				{
					this.allomorphAtribute = allomorphAtribute;
				}

				@JacksonXmlText
				public void setSuffixValue(final String suffixValue)
				{
					this.suffixValue = suffixValue;
				}

				@JacksonXmlElementWrapper(useWrapping = false)
				@JacksonXmlProperty(localName = "allomorph")
				public void setAllomorphList(final List<Allomorph> allomorphList)
				{
					this.allomorphList = allomorphList;
				}

				public static class Allomorph
				{
					@JacksonXmlText
					private String allomorphValue;

					public String getAllomorphValue()
					{
						return this.allomorphValue;
					}

					@JacksonXmlText
					public void setAllomorphValue(final String allomorphValue)
					{
						this.allomorphValue = allomorphValue;
					}
				}
			}

			public static class Join
			{
				@JacksonXmlProperty(localName = "br")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Br> brList = new ArrayList<>();

				public List<Br> getBrList()
				{
					return this.brList;
				}

				@JacksonXmlElementWrapper(useWrapping = false)
				@JacksonXmlProperty(localName = "br")
				public void setBrList(final List<Br> brList)
				{
					this.brList = brList;
				}
			}

			public static class Pass
			{
				@JacksonXmlProperty(localName = "br")
				@JacksonXmlElementWrapper(useWrapping = false)
				private List<Br> brList = new ArrayList<>();

				public List<Br> getBrList()
				{
					return this.brList;
				}

				@JacksonXmlElementWrapper(useWrapping = false)
				@JacksonXmlProperty(localName = "br")
				public void setBrList(final List<Br> brList)
				{
					this.brList = brList;
				}
			}

			public static class Br
			{
				@JacksonXmlText
				private String brText;

				public String getBrText()
				{
					return this.brText;
				}

				@JacksonXmlText
				public void setBrText(final String brText)
				{
					this.brText = brText;
				}
			}
		}
	}
}
