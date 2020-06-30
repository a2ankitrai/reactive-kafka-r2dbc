SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Address](
                                [id] [int] IDENTITY(0,1) NOT NULL,
                                [person_id] [int] NOT NULL,
                                [house_number] [varchar](100) NULL,
                                [street] [varchar](100) NULL,
                                [city] [varchar](100) NULL,
                                [zip_code] [varchar](100) NULL
) ON [PRIMARY]
GO
