<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<xsl:choose>
					<xsl:when test="catalog/cd">
	<!-- 				<h2>Indexs</h2> -->
					<table border="1">
	<!-- 					<tr bgcolor="#9acd32"> -->
	<!-- 						<th>Index</th> -->
	<!-- 						<th>Count</th> -->
	<!-- 					</tr> -->
						<xsl:for-each select="catalog/cd">
							<tr>
								<td>
									<xsl:value-of select="index" />
								</td>
								<td>
									<xsl:value-of select="count" />
								</td>
							</tr>
						</xsl:for-each>
					</table>
					</xsl:when>
					<xsl:otherwise>No item was found
					</xsl:otherwise>
				</xsl:choose>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>