SET ANSI_NULLS ON
    GO
SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Person](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [first_name] [varchar](100) NULL,
    [last_name] [varchar](100) NULL,
    [contact_number] [varchar](100) NULL
    ) ON [PRIMARY]
    GO
