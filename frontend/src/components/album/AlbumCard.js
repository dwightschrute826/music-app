import {
  Card,
  CardMedia,
  CardContent,
  Typography,
  IconButton,
  Box,
  Menu,
  MenuItem,
} from "@mui/material";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import AlbumPhotoPlaceholder from "../placeholder/AlbumPhotoPlaceholder";
import { Fragment, useState } from "react";

export default function AlbumCard({
  album,
  onChangeAlbumToDeleteHandler,
  openFormHandler,
  onAlbumClick, // Add onAlbumClick prop
}) {
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);

  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const openEditForm = () => {
    openFormHandler(0, album);
    handleClose();
  };

  const deleteAlbum = () => {
    onChangeAlbumToDeleteHandler(album);
    handleClose();
  };

  const handleAlbumClick = () => {
    if (onAlbumClick) {
      onAlbumClick(album.id); // Trigger the click handler with the album's ID
    }
  };

  return (
    <Fragment>
      <Box
        sx={{
          position: "relative",
          cursor: "pointer", // Add cursor pointer for clickability
        }}
        onClick={handleAlbumClick} // Add click event for album selection
      >
        <Box sx={{ position: "absolute", top: 5, right: 2 }}>
          <IconButton
            id="options"
            aria-label="options"
            onClick={(e) => {
              e.stopPropagation(); // Prevent triggering album click
              handleClick(e);
            }}
          >
            <MoreVertIcon sx={{ color: "rgba(255,255,255,1)" }} />
          </IconButton>
        </Box>
        <Card
          sx={{
            maxWidth: 215,
            background: "transparent",
            color: "#FFFFFF",
          }}
          elevation={0}
        >
          {album.urlCoverPhoto ? (
            <CardMedia
              component="img"
              alt={album.title}
              height="215"
              sx={{
                borderRadius: "10px",
                transition: ".2s ease-in-out",
              }}
              image={album.urlCoverPhoto}
            />
          ) : (
            <AlbumPhotoPlaceholder message={album.title} operation={2} />
          )}

          <CardContent
            sx={{
              background: "transparent",
              p: 2,
              "&:last-child": {
                paddingBottom: 0,
              },
            }}
          >
            <Typography
              variant="h5"
              component="div"
              noWrap={true}
              align="center"
              sx={{ fontWeight: 600 }}
            >
              {album.title}
            </Typography>
            <Typography variant="body2" align="center">
              {album.artist}
            </Typography>
          </CardContent>
        </Card>
      </Box>

      <Menu
        id="options-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          "aria-labelledby": "options",
        }}
      >
        <MenuItem onClick={openEditForm}>Edit</MenuItem>
        <MenuItem onClick={deleteAlbum}>Delete</MenuItem>
      </Menu>
    </Fragment>
  );
}
